package com.example.generatekanji.presentation.controllers

import com.example.generatekanji.application.services.WordAndStatService
import com.example.generatekanji.domain.dto.WordAndStat
import com.example.generatekanji.domain.shared.WordAndStatShared
import com.example.generatekanji.domain.view.RandomWordView
import com.example.generatekanji.domain.view.WordAndStatViewRandom
import io.swagger.v3.oas.annotations.Operation
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

    @GetMapping("/random")
    @Operation(description = "Get Random WordAndStat")
    fun getRandomWordAndStat(principal: Principal): WordAndStatViewRandom {
        return wordAndStatService.getRandomWordAndStat(principal)
    }

    @GetMapping
    @Operation(description = "Get WordAndStat")
    fun getWordAndStat(id: String): Optional<WordAndStat> {
        return wordAndStatService.findById(id)
    }

    @PutMapping("/afterQuiz")
    @Operation(description = "Update after quiz")
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