package com.example.generatekanji.presentation.controllers

import com.example.generatekanji.application.services.WordAndStatService
import com.example.generatekanji.domain.shared.WordAndStatShared
import com.example.generatekanji.domain.view.WordAndStatView
import com.example.generatekanji.domain.view.WordAndStatViewRandom
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*

@RestController
@Tag(name = "WordAndStat API")
@RequestMapping("/api/web-client/wordAndStat")
@CrossOrigin
class WordAndStatController(
    val wordAndStatService: WordAndStatService
) {

    @GetMapping("{id}")
    @Operation(description = "Get WordAndStat", security = [SecurityRequirement(name = "bearerAuth")])
    fun getWordAndStat(@PathVariable id: String): WordAndStatView {
        return wordAndStatService.findById(id)
    }

    @PutMapping("/afterQuiz")
    @Operation(description = "Update after quiz", security = [SecurityRequirement(name = "bearerAuth")])
    fun updateAfterQuiz(@RequestBody wordAndStat: WordAndStatViewRandom, principal: Principal) {
        wordAndStatService.updateAfterQuiz(wordAndStat, principal)
    }

    @PutMapping
    @Operation(description = "Update WordAndStat")
    fun updateWordAndStat() {
    }

    @PostMapping
    @Operation(description = "Create WordAndStat")
    fun createWordAndStat(wordAndStatShared: WordAndStatShared, principal: Principal) {
        wordAndStatService.save(wordAndStatShared, principal)
    }


    @DeleteMapping
    @Operation(description = "Delete WordAndStat")
    fun delete(id: String) {
//        wordAndStatService.deleteById(id)
    }

}