package com.example.generatekanji.application.controllers

import com.example.generatekanji.application.services.TableService
import com.example.generatekanji.application.services.TranslatePageService
import com.example.generatekanji.application.services.WordsService
import com.example.generatekanji.domain.dto.Level
import com.example.generatekanji.domain.dto.Word

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/examples")
@Tag(name = "tag", description = "ddd")
class WordsController(val wordsService: WordsService, val tableService: TableService,
                      val translatePageService: TranslatePageService) {


    val listWord:List<Word> = listOf(
        Word("1231","word1","translate1",Level.N1),
        Word("1232","word2","translate2",Level.N1),
        Word("1233","word3","translate3",Level.N1),
        Word("1234","word4","translate4",Level.N1)


    )


    @GetMapping("/getall")
    @Operation(description = "getAllWords")
    fun index():List<Word> = wordsService.findWords()


    @PostMapping("/")
    @Operation(description = "Post new words")
    fun post(@RequestBody word: Word){
        wordsService.save(word)
    }

//    @GetMapping("/page")
//    @Operation(description = "CreateFile")
//    fun getPageCreatedName():String{
//
//        return  tableService.createTable(listWord)
//    }

    @PostMapping("/generateAll")
    fun generateAll(){
        val fileName=tableService.createTable(listWord)
        translatePageService.createTranslatePage(fileName)


    }
}