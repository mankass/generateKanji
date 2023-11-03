package com.example.generatekanji.application.services

import com.example.generatekanji.domain.dto.UserData
import com.example.generatekanji.domain.dto.WordAndStat
import com.example.generatekanji.domain.enums.AnswerStatus
import com.example.generatekanji.domain.shared.WordAndStatShared
import com.example.generatekanji.domain.view.QuizView
import com.example.generatekanji.domain.view.RandomWordView
import com.example.generatekanji.domain.view.WordAndStatViewRandom
import com.example.generatekanji.infra.UserRepository
import com.example.generatekanji.infra.WordAndStatRepository
import com.example.generatekanji.infra.WordRepository
import org.springframework.stereotype.Service
import java.security.Principal
import java.util.*
import kotlin.random.Random

@Service
class WordAndStatService(
    val wordAndStatRepository: WordAndStatRepository,
    val userRepository: UserRepository,
    val wordRepository: WordRepository,
    val wordService: WordService
) {
    fun save(wordAndStatShared: WordAndStatShared, principal: Principal) {
        val user = userRepository.findByLogin(principal.name)
        val wordAndStat = wordAndStatRepository.findByUserDataAndAndWordData(user, wordAndStatShared.wordData)

        if (wordAndStat != null) {
            when (wordAndStatShared.answerStatus) {
                AnswerStatus.CORRECT -> wordAndStat.correctAttempts += 1
                AnswerStatus.INCORRECT -> wordAndStat.wrongAttempts += 1
            }
            wordAndStat.percentCorrect = (wordAndStat.correctAttempts) /
                    (wordAndStat.correctAttempts + wordAndStat.wrongAttempts)
            wordAndStatRepository.save(wordAndStat)
        }


        wordAndStatRepository.save(
            WordAndStat(
                wordAndStatShared.wordData, user,
                wordAndStatShared.wrongAttempts, wordAndStatShared.correctAttempts,
                (wordAndStatShared.wrongAttempts / wordAndStatShared.correctAttempts),
                null
            )
        )
    }

    fun findById(id: String): Optional<WordAndStat> {
        return wordAndStatRepository.findById(id)
    }

    fun findAllWordAndStatToUser(userData: UserData): List<WordAndStat> {
        return wordAndStatRepository.findByUserData(userData)
    }

    fun getRandomWordAndStat(principal: Principal): WordAndStatViewRandom {
        val user = userRepository.findByLogin(principal.name)
        val all = wordService.getAllWords()

        val wordAndStat = wordAndStatRepository.findByUserData(user).random()
        val listAnswers = mutableListOf(
            all[Random.nextInt(all.size)].transcription,
            all[Random.nextInt(all.size)].transcription,
            all[Random.nextInt(all.size)].transcription,
            wordAndStat.wordData.transcription
        )
        return WordAndStatViewRandom(
            wordAndStat.id!!,
            wordAndStat.wordData.word, wordAndStat.wordData.translate,
            wordAndStat.wordData.transcription,
            listAnswers, wordAndStat.wrongAttempts,
            wordAndStat.correctAttempts, wordAndStat.percentCorrect
        )
    }

    fun createFromRandom(quizView: QuizView, principal: Principal) {
        val word = wordRepository.findById(quizView.wordId)
        val user = userRepository.findByLogin(principal.name)

        wordAndStatRepository.save(WordAndStat(word.get(), user, 0, 1, 100, null))

    }

    fun updateAfterQuiz(wordAndStat: WordAndStatViewRandom, principal: Principal) {
        val wordAndStatId = wordAndStatRepository.findById(wordAndStat.id)
        val user = userRepository.findByLogin(principal.name)
        val percent: Double =
            wordAndStat.correctAttempts / (wordAndStat.correctAttempts + wordAndStat.wrongAttempts).toDouble() * 100
        wordAndStatRepository.save(
            WordAndStat(
                wordAndStatId.get().wordData, user, wordAndStat.wrongAttempts,
                wordAndStat.correctAttempts,
                (percent.toInt()),
                wordAndStat.id
            )
        )
    }
}