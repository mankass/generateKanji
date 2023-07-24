package com.example.generatekanji.application.controllers

import com.example.generatekanji.application.services.TableService
import com.example.generatekanji.application.services.TranslatePageService
import com.example.generatekanji.application.services.WordsService
import com.example.generatekanji.domain.dto.Word
import com.example.generatekanji.domain.dto.WordData
import com.example.generatekanji.infra.WordRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/examples")
@Tag(name = "tag", description = "ddd")
class WordsController(
    val wordRepository: WordRepository,
    val wordsService: WordsService, val tableService: TableService,
    val translatePageService: TranslatePageService
) {


    @GetMapping("/get-all222")
    @Operation(description = "getAllWords")
    fun getALlWords(): List<Word> {
        val allWordsList = wordsService.findWords()
        return randomGenerator(allWordsList).take(60).toList()

    }

    fun <T> randomGenerator(words: List<T>) = sequence {
        while (true) {
            words.shuffled().forEach { yield(it) }
        }
    }


    @PostMapping("/")
    @Operation(description = "Post new words")
    fun post(@RequestBody word: Word) {
        wordsService.save(word)
    }


    @PostMapping("/generateAll")
    fun generateAll(): ResponseEntity<String> {
        val listWordsFromDb = getALlWords()
        val stringListPair: Pair<String, List<Word>> = tableService.createTable(listWordsFromDb)
        translatePageService.createTranslatePage(Pair(stringListPair.first, listWordsFromDb))
        return ResponseEntity.ok(stringListPair.first)
    }


    @GetMapping("/get-all")
    @Operation(description = "getAllWords")
    fun getAllWords2(): MutableIterable<WordData> {

       return wordRepository.findAll();
    }

    @PostMapping("/word")
    fun createWord(@Valid @RequestBody wordData: WordData){
        wordRepository.save(wordData)
    }


}