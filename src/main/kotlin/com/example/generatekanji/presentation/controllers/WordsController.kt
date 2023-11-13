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
    fun <T> randomGenerator(words: List<T>) = sequence {
        while (true) {
            words.shuffled().forEach { yield(it) }
        }
    }

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

    @GetMapping("/random")
    @Operation(description = "Get Random")
    fun getRandomWord(): RandomWordView{
         return wordService.getRandom()
    }

    @GetMapping("/translate-quiz")
    @Operation(description = "Get random translate quiz")
    fun getRandomTranslateQuiz():RandomTranslateWordView{
        return wordService.getRandomTranslateQuiz()
    }

    @GetMapping("/generate-today")
    @Operation(description = "Get all today words")
    fun generateToday(date: LocalDate){
        wordService.generateWordsByDate(date)
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
            WordData(wordView.word, wordView.translate, LocalDate.now(),wordView.transcription, UUID.randomUUID().toString())
        wordRepository.save(wordData)
    }

    @GetMapping("/getAnswers")
    @Produces("document/docx")
    fun downloadAnswers(string: String): ResponseEntity<ByteArrayResource> {
        val file= Path("C:\\Users\\Даниил\\IdeaProjects\\generateKanji_new\\183f999e-47bd-4591-a098-79e6c3885205.docx")
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+file.fileName+"\"").body(ByteArrayResource(Files.readAllBytes(file)))
    }

    @GetMapping("/getWords")
    @Produces("document/docx")
    fun downloadWords(string: String): ResponseEntity<ByteArrayResource> {
        val file = Path("C:\\Users\\Даниил\\IdeaProjects\\generateKanji_new\\183f999e-47bd-4591-a098-79e6c3885205.xlsx")
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.fileName + "\"")
            .body(ByteArrayResource(Files.readAllBytes(file)))
    }

    @DeleteMapping
    @Operation(description = "deleteWord")
    fun deleteWord(@RequestParam id: String) {
        wordService.deleteWord(id)
    }

    @GetMapping("/getAllToday")
    @Operation(description = "downloadToday")
    fun downloadAllToday(): ResponseEntity<ByteArrayResource> {
        val list = listOf(
            "C:\\Users\\Даниил\\IdeaProjects\\generateKanji_new\\3bfbad49-ef8b-408c-9ea8-7072dd6de9c7.docx",
            "C:\\Users\\Даниил\\IdeaProjects\\generateKanji_new\\allb0a960ae-7968-4422-9301-713216190b93.xlsx"
        )
        val byteArrayOutputStream = ByteArrayOutputStream()
        val bufferedOutputStream = BufferedOutputStream(byteArrayOutputStream)
        val zipOutputStream = ZipOutputStream(bufferedOutputStream)


        for (string in list) {
            val fileSystemResource = FileSystemResource(string)
            val zip: ZipEntry = ZipEntry(fileSystemResource.filename)
            zip.size = fileSystemResource.contentLength()
            zip.time = System.currentTimeMillis()
            zipOutputStream.putNextEntry(zip)
            StreamUtils.copy(fileSystemResource.inputStream, zipOutputStream)
            zipOutputStream.closeEntry()
        }
        zipOutputStream.finish()
        val byteArrayResource=ByteArrayResource(byteArrayOutputStream.toByteArray())

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+"file.zip"+"\"").body(byteArrayResource)
    }

}