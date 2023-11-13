package com.example.generatekanji.presentation.controllers

import com.example.generatekanji.application.services.WordService
import com.example.generatekanji.application.views.WordView
import com.example.generatekanji.domain.dto.WordData
import com.example.generatekanji.domain.view.RandomTranslateWordView
import com.example.generatekanji.domain.view.RandomWordView
import com.example.generatekanji.infra.WordRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.FileSystemResource
import org.springframework.http.ResponseEntity
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.*
import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream
import java.nio.file.Files
import java.time.LocalDate
import java.util.*
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import javax.ws.rs.Produces
import javax.ws.rs.core.HttpHeaders
import kotlin.io.path.Path


@RestController
@RequestMapping("api/web-client")
@CrossOrigin
@Tag(name = "API", description = "ddd")
class WordsController(
    val wordRepository: WordRepository,
    val wordService: WordService,
) {

    @GetMapping("/getAllPaging")
    @Operation(description = "getAllByPagination")
    fun getAllWordsByPagination(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "0") limit: Int
    ): List<WordData> {
        return wordService.getAllWords(page, limit);
    }


    @GetMapping("/day")
    @Operation(description = "Get all words by date")
    fun getAllWordsToday(localDate: LocalDate): List<WordData> {
        return wordService.getWordsByDate(localDate)
    }

    @GetMapping("/getByWord")
    @Operation(description = "searchWordByName")
    fun searchWordByName(string: String): List<WordData> {
        return wordService.getWordsByName(string)
    }

    @GetMapping("/getByTranslate")
    @Operation(description = "searchWordByTranslate")
    fun searchWordByTranslate(string: String): List<WordData> {
        return wordService.getWordsByTranslate(string)
    }

    @GetMapping("/get-all")
    @Operation(description = "getAllWords")
    fun getAllWords(): List<WordData> {
        return wordService.getAllWords()
    }

    @PostMapping("/delete")
    fun deleteALl() {
        wordRepository.deleteAll()
    }

    @PostMapping("/word")
    fun createWord(@Valid @RequestBody wordView: WordView) {
        val wordData =
            WordData(
                wordView.word,
                wordView.translate,
                LocalDate.now(),
                wordView.transcription,
                UUID.randomUUID().toString()
            )
        wordRepository.save(wordData)
    }

    @DeleteMapping
    @Operation(description = "deleteWord")
    fun deleteWord(@RequestParam id: String) {
        wordService.deleteWord(id)
    }

}