package com.example.generatekanji.application.services

import com.example.generatekanji.domain.dto.WordData
import com.example.generatekanji.domain.view.RandomTranslateWordView
import com.example.generatekanji.domain.view.RandomWordView
import com.example.generatekanji.infra.WordRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import kotlin.random.Random

@Service
class WordService(
    val wordRepository: WordRepository
) {
    fun <T> randomGenerator(words: List<T>) = sequence {
        while (true) {
            words.shuffled().forEach { yield(it) }
        }
    }

    fun <T> shuffle(list: MutableList<T>) {
        list.shuffle()
    }

    fun getRandom(): RandomWordView {
        val all = getAllWords()
        val word = all[Random.nextInt(all.size)]
        val listAnswers = mutableListOf(
            all[Random.nextInt(all.size)].transcription,
            all[Random.nextInt(all.size)].transcription,
            all[Random.nextInt(all.size)].transcription,
            word.transcription
        )
        shuffle(listAnswers)

        return RandomWordView(word.word, word.translate, word.transcription, listAnswers)
    }

    fun getWordsByDate(localDate: LocalDate): List<WordData> {
        return wordRepository.findAll().toList().filter { wordData -> wordData.createdData == localDate }
    }

    fun getAllWords(): List<WordData> {
        return wordRepository.findAll().toList()
    }

    fun generateRandomWordsList(): List<WordData> {
        return randomGenerator(getAllWords()).take(60).toList()
    }

    fun getRandomTranslateQuiz(): RandomTranslateWordView {
        val all = getAllWords()
        val word = all[Random.nextInt(all.size)]
        val listAnswers = mutableListOf(
            all[Random.nextInt(all.size)].translate,
            all[Random.nextInt(all.size)].translate,
            all[Random.nextInt(all.size)].translate,
            word.translate
        )
        shuffle(listAnswers)

        return RandomTranslateWordView(word.word, word.translate, word.transcription, listAnswers)
    }
}