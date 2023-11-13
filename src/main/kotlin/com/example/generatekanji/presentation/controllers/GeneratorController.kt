package com.example.generatekanji.presentation.controllers

import com.example.generatekanji.application.services.GeneratorService
import com.example.generatekanji.domain.enums.GeneratorTimeType
import com.example.generatekanji.domain.enums.GeneratorType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "API")
@RequestMapping("/api/web-client/")
@CrossOrigin
class GeneratorController(
    val generatorService: GeneratorService,
) {
    @Operation(description = "getAllInZip")
    @GetMapping("/all-zip", produces = ["application/zip"])
    fun getAllInZip(
        @RequestParam(required = true) generatorTimeType: GeneratorTimeType,
        @RequestParam(required = true) generatorType: GeneratorType
//        ,principal: Principal
    ): ResponseEntity<ByteArrayResource> {
        val name = "dan"
        val byte = generatorService.generate(generatorTimeType, generatorType, name)

        return ResponseEntity.ok()
            .contentLength(byte.size.toLong())
            .body(ByteArrayResource(byte))

    }

}