package com.example.generatekanji.application.services

import com.example.generatekanji.domain.dto.WordData
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
    fun getRandom(): RandomWordView {
        val all = wordRepository.findAll().toList()
        val word=all[Random.nextInt(all.size)]
        return RandomWordView(word.word,word.translate,all[Random.nextInt(all.size)].transcription,
            all[Random.nextInt(all.size)].transcription,all[Random.nextInt(all.size)].transcription,
            all[Random.nextInt(all.size)].transcription)
    }

    fun getWordsByDate(localDate: LocalDate): List<WordData> {
       return wordRepository.findAll().toList().filter { wordData -> wordData.createdData == localDate }
    }

    fun getAllWords(): List<WordData> {
        return wordRepository.findAll().toList()
    }
}