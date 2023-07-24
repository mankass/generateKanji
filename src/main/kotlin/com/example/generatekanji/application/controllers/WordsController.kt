package com.example.generatekanji.application.controllers

import com.example.generatekanji.application.services.TableService
import com.example.generatekanji.application.services.TranslatePageService
import com.example.generatekanji.application.views.WordView
import com.example.generatekanji.domain.dto.WordData
import com.example.generatekanji.infra.WordRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/v1/examples")
@Tag(name = "tag", description = "ddd")
class WordsController(
    val wordRepository: WordRepository,
    val tableService: TableService,
    val translatePageService: TranslatePageService
) {
    fun <T> randomGenerator(words: List<T>) = sequence {
        while (true) {
            words.shuffled().forEach { yield(it) }
        }
    }

    @GetMapping("/today")
    @Operation(description = "Get all today words")
    fun getAllWordsToday(): List<WordData> {
        return getAllWords().filter { wordData -> wordData.createdData == LocalDate.now() }
    }

    @GetMapping("/generate-today")
    @Operation(description = "Get all today words")
    fun generateToday(): ResponseEntity<String> {
        val listWordsFromDb = randomGenerator(getAllWordsToday()).take(60).toList()
        val stringPair = tableService.createTable(listWordsFromDb)
        translatePageService.createTranslatePage(Pair(stringPair.first, listWordsFromDb))
        return ResponseEntity.ok(stringPair.first)
    }


    @PostMapping("/generateAll")
    fun generateAll(): ResponseEntity<String> {
        val listWordsFromDb = randomGenerator(getAllWords()).take(60).toList()
        val stringPair = tableService.createTable(listWordsFromDb)
        translatePageService.createTranslatePage(Pair(stringPair.first, listWordsFromDb))
        return ResponseEntity.ok(stringPair.first)
    }


    @GetMapping("/get-all")
    @Operation(description = "getAllWords")
    fun getAllWords(): List<WordData> {
        return wordRepository.findAll().toList()
    }

    @PostMapping("/delete")
    fun deleteALl() {
        wordRepository.deleteAll()
    }

    @PostMapping("/word")
    fun createWord(@Valid @RequestBody wordView: WordView) {
        val wordData =
            WordData(wordView.word, wordView.translate, LocalDate.now(),wordView.transcription, UUID.randomUUID().toString())
        wordRepository.save(wordData)
    }


}