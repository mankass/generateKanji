package com.example.generatekanji.presentation.controllers

import com.example.generatekanji.application.services.WordAndStatService
import com.example.generatekanji.domain.dto.UserData
import com.example.generatekanji.domain.shared.WordAndStatShared
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping

class WordAndStatController(
    val wordAndStatService: WordAndStatService
) {


    @GetMapping
    @Operation(description = "Get WordAndStat")
    fun getDeck(id: String) {
//        return   wordAndStatService.findById(id)
    }

    @PutMapping
    @Operation(description = "Update WordAndStat")
    fun updateDeck() {
    }

    @PostMapping
    @Operation(description = "Crate WordAndStat")
    fun createDeck(wordAndStatShared: WordAndStatShared, userData: UserData) {
        wordAndStatService.save(wordAndStatShared, userData)
    }

    @DeleteMapping
    @Operation(description = "Delete WordAndStat")
    fun delete(id: String) {
//        wordAndStatService.deleteById(id)
    }


}