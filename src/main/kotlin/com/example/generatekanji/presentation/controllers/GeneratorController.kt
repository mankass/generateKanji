package com.example.generatekanji.presentation.controllers

import com.example.generatekanji.GenerateKanjiApplication
import com.example.generatekanji.application.services.GeneratorService
import com.example.generatekanji.domain.enums.GeneratorTimeType
import com.example.generatekanji.domain.enums.GeneratorType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@Tag(name = "API")
@RequestMapping("/api/web-client/")
@CrossOrigin
class GeneratorController(
    val generatorService: GeneratorService,
    private val generate: GenerateKanjiApplication
) {
    @Operation(description = "getAllInZip")
    @GetMapping("/all-zip")
    fun getAllInZip(
        @RequestParam(required = true) generatorTimeType: GeneratorTimeType,
        @RequestParam(required = true) generatorType: GeneratorType
//        ,principal: Principal
    ) {

        generatorService.generate(generatorTimeType, generatorType)
    }
}