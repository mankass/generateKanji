package com.example.generatekanji.application.services

import com.example.generatekanji.domain.dto.WordData
import com.example.generatekanji.domain.view.RandomTranslateWordView
import com.example.generatekanji.domain.view.RandomWordView
import com.example.generatekanji.infra.WordRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.time.LocalDate
import kotlin.random.Random

@Service
class WordService(
    val wordRepository: WordRepository,
    val filesService: FilesService
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

        return RandomWordView(word.id, word.word, word.translate, word.transcription, listAnswers)
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

    fun generateWordsByDate(date: LocalDate) {
        val listWordsFromDb = randomGenerator(getWordsByDate(date).take(60)).toList()
        filesService.createAll(listWordsFromDb)
    }

    fun getWordsByName(string: String): List<WordData> {
        return wordRepository.findByWord(string)
    }

    fun getWordsByTranslate(string: String): List<WordData> {
        return wordRepository.findByTranslate(string)
    }

    fun deleteWord(id: String) {
        wordRepository.deleteById(id)

    }

    fun getAllWords(page: Int, limit: Int): List<WordData> {
        return wordRepository.findByOrderByWord(PageRequest.of(page, limit))
    }
}