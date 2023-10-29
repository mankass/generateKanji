package com.example.generatekanji.application.services

import com.example.generatekanji.domain.view.QuizView
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class QuizService(
    val wordService: WordService,
    val wordAndStatService: WordAndStatService
) {


    fun getTranslateQuiz(): QuizView {

        val all = wordService.getAllWords()
        val word = all[Random.nextInt(all.size)]
        val listAnswers = mutableListOf(
            all[Random.nextInt(all.size)].translate,
            all[Random.nextInt(all.size)].translate,
            all[Random.nextInt(all.size)].translate,
            all[Random.nextInt(all.size)].translate,
            all[Random.nextInt(all.size)].translate,
            word.translate
        )
        wordService.shuffle(listAnswers)

        return QuizView(word.word, word.translate, word.transcription.orEmpty(), listAnswers)
    }

    fun getKanjiQuiz(): QuizView {
        val all = wordService.getAllWords()
        val word = all[Random.nextInt(all.size)]
        val listAnswers = mutableListOf(
            all[Random.nextInt(all.size)].word,
            all[Random.nextInt(all.size)].word,
            all[Random.nextInt(all.size)].word,
            all[Random.nextInt(all.size)].word,
            all[Random.nextInt(all.size)].word,
            word.word
        )
        wordService.shuffle(listAnswers)

        return QuizView(word.transcription.orEmpty(), word.translate, word.word, listAnswers)
    }

    fun getTranscriptionQuiz(): QuizView {
        val all = wordService.getAllWords()
        val word = all[Random.nextInt(all.size)]
        val listAnswers = mutableListOf(
            all[Random.nextInt(all.size)].transcription.orEmpty(),
            all[Random.nextInt(all.size)].transcription.orEmpty(),
            all[Random.nextInt(all.size)].transcription.orEmpty(),
            all[Random.nextInt(all.size)].transcription.orEmpty(),
            all[Random.nextInt(all.size)].transcription.orEmpty(),
            word.transcription.orEmpty()
        )
        wordService.shuffle(listAnswers)

        return QuizView(word.word, word.transcription.orEmpty(), word.translate, listAnswers)

    }
}
