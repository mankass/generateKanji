package com.example.generatekanji.presentation.controllers

import com.example.generatekanji.application.services.DeckService
import com.example.generatekanji.domain.dto.DeckData
import com.example.generatekanji.domain.view.DeckView
import com.example.generatekanji.infra.DeckRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*

@RestController
@Tag(name = "Deck API")
@RequestMapping("/api/web-client/deck")
@CrossOrigin
class DeckController(
    val deckRepository: DeckRepository,
    val deckService: DeckService
) {


    @GetMapping
    @Operation(description = "Get deck")
    fun getDeck(id: String): Optional<DeckData> {
        return deckRepository.findById(id)
    }

    @PutMapping
    @Operation(description = "Update deck")
    fun updateDeck(@RequestParam idDeck: String) {
        deckService.updateDeck(idDeck)
    }

    @PostMapping
    @Operation(description = "Crate deck")
    fun createDeck(@RequestParam name: String, principal: Principal): HttpStatus {
        deckService.createDeck(name, principal)
        return HttpStatus.OK
    }

    @DeleteMapping
    @Operation(description = "Delete deck")
    fun delete(id: String) {
        deckRepository.deleteById(id)
    }

    @GetMapping("/get-all")
    @Operation(description = "get all decks")
    fun getALlDecks(principal: Principal): List<DeckView> {
        println(principal)
        return deckService.getALl(principal)
    }

    @PutMapping("/add-word-toDeck")
    @Operation(description = "add word to deck")
    fun addWordToDeck(@RequestParam idDeck: String, @RequestParam wordId: String, principal: Principal) {
        deckService.addWordToDeck(idDeck, wordId, principal)
    }

    @PostMapping("/addUserToDeck")
    @Operation(description = "addUserToDeck")
    fun addUserToDeck(@RequestParam login: String, @RequestParam deckId: String) {
        deckService.addUserToDeck(login, deckId)
    }

    @PostMapping("/deleteUserFromDeck")
    @Operation(description = "deleteUserFromDeck")
    fun deleteUserFromDeck(@RequestParam login: String, @RequestParam deckId: String) {
        deckService.deleteUserFromDeck(login, deckId)
    }

}