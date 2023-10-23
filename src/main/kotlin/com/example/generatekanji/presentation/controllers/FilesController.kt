package com.example.generatekanji.presentation.controllers

import com.example.generatekanji.application.services.FilesService
import com.example.generatekanji.application.services.WordService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/web-client/files")
@Tag(name = "Files API")
@CrossOrigin
class FilesController(
    val filesService: FilesService,
    val wordService: WordService,
) {
    @GetMapping("/files")
    @Operation(description = "Create files")
    fun getAllWordsToday() {
        filesService.createAll(wordService.generateRandomWordsList())
    }
}