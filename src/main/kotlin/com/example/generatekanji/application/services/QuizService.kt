package com.example.generatekanji.application.services

import com.example.generatekanji.domain.enums.TypeQuiz
import com.example.generatekanji.domain.view.QuizPersonalView
import com.example.generatekanji.domain.view.QuizView
import com.example.generatekanji.infra.UserRepository
import org.springframework.stereotype.Service
import java.security.Principal
import kotlin.random.Random

@Service
class QuizService(
    val wordService: WordService,
    val wordAndStatService: WordAndStatService,
    val userRepository: UserRepository,
) {


    fun getTranslateQuiz(): QuizView {
        val all = wordService.getAllWords()
        val word = all[Random.nextInt(all.size)]

        return QuizView(
            word.id,
            word.word,
            word.translate,
            word.transcription.orEmpty(),
            createAnswersList(word.translate, quizType = TypeQuiz.TRANSLATE),
            TypeQuiz.TRANSLATE
        )
    }

    fun getKanjiQuiz(): QuizView {
        val all = wordService.getAllWords()
        val word = all[Random.nextInt(all.size)]

        return QuizView(
            word.id, word.transcription.orEmpty(), word.word, word.translate,
            createAnswersList(word.word, quizType = TypeQuiz.KANJI), TypeQuiz.KANJI
        )
    }

    fun getTranscriptionQuiz(): QuizView {
        val all = wordService.getAllWords()
        val word = all[Random.nextInt(all.size)]

        return QuizView(
            word.id,
            word.word,
            word.transcription.orEmpty(),
            word.translate,
            createAnswersList(word.transcription.orEmpty(), quizType = TypeQuiz.TRANSCRIPTION),
            TypeQuiz.TRANSCRIPTION
        )

    }

    fun getQuizByType(quizType: TypeQuiz, principal: Principal?): QuizView? {
        return when (quizType) {
            TypeQuiz.TRANSLATE -> getTranslateQuiz()
            TypeQuiz.KANJI -> getKanjiQuiz()
            TypeQuiz.TRANSCRIPTION -> getTranscriptionQuiz()
            TypeQuiz.PERSONAL -> getPersonalQuiz(principal)
        }
    }

    private fun getPersonalQuiz(principal: Principal?): QuizView {
        val user = userRepository.findByLogin(principal?.name)
        val wordAndStat = wordAndStatService.getWordForQuiz(user)
        val word = wordAndStat?.wordData

        if (word != null) {
            return QuizPersonalView(
                word.id,
                word.transcription.orEmpty(),
                word.word,
                word.translate,
                createAnswersList(word.word, quizType = TypeQuiz.PERSONAL),
                TypeQuiz.PERSONAL,
                wordAndStat.percentCorrect
            )
        }
        throw Exception("Something wrong with getPersonalQuiz")
    }

    fun createFromQuiz(quizView: QuizView, principal: Principal) {
        wordAndStatService.createFromRandom(quizView, principal)
    }

    fun createAnswersList(answer: String, quizType: TypeQuiz): MutableList<String> {
        val all = wordService.getAllWords()
        val answersList = mutableListOf<String>()
        when (quizType) {
            TypeQuiz.TRANSLATE -> while (answersList.size < 5) {
                answersList.add(all[Random.nextInt(all.size)].translate)
            }

            TypeQuiz.KANJI -> while (answersList.size < 5) {
                answersList.add(all[Random.nextInt(all.size)].word)
            }

            TypeQuiz.TRANSCRIPTION -> while (answersList.size < 5) {
                answersList.add(all[Random.nextInt(all.size)].transcription.orEmpty())
            }

            TypeQuiz.PERSONAL -> while (answersList.size < 5) {
                answersList.add(all[Random.nextInt(all.size)].word)
            }
        }
        answersList.add(answer)

        return answersList
    }
}
