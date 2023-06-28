package com.example.generatekanji.application.controllers

import com.example.generatekanji.application.services.TableService
import com.example.generatekanji.application.services.TranslatePageService
import com.example.generatekanji.application.services.WordsService
import com.example.generatekanji.domain.dto.Word

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/examples")
@Tag(name = "tag", description = "ddd")
class WordsController(
    val wordsService: WordsService, val tableService: TableService,
    val translatePageService: TranslatePageService
) {


    @GetMapping("/get-all")
    @Operation(description = "getAllWords")
    fun getALlWords(): List<Word> = wordsService.findWords()


    @PostMapping("/")
    @Operation(description = "Post new words")
    fun post(@RequestBody word: Word) {
        wordsService.save(word)
    }


    @PostMapping("/generateAll")
    fun generateAll(): ResponseEntity<String> {
        val listWordsFromDb = getALlWords()
        val stringListPair = tableService.createTable(listWordsFromDb)
        translatePageService.createTranslatePage(stringListPair)
        return ResponseEntity.ok(stringListPair.first)


    }
}