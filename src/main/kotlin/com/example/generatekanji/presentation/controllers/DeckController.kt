package com.example.generatekanji.presentation.controllers

import com.example.generatekanji.application.services.DeckService
import com.example.generatekanji.domain.dto.DeckData
import com.example.generatekanji.domain.view.DeckView
import com.example.generatekanji.infra.DeckRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*

@RestController
@Tag(name = "Deck API")
@RequestMapping("/api/web-client/deck")
@CrossOrigin
class DeckController(
    val deckRepository: DeckRepository, val deckService: DeckService
) {

    @GetMapping
    @Operation(description = "Get deck")
    fun getDeck(id: String): Optional<DeckData> {
        return deckService.findById(id)
    }

    @PutMapping
    @Operation(description = "Update deck")
    fun updateDeck(@RequestParam idDeck: String): ResponseEntity<String> {
        deckService.updateDeck(idDeck)
        return ResponseEntity.ok().build()
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

    @DeleteMapping("/deleteWordFromDeck")
    @Operation(description = "delete from deck")
    fun deleteWordFromDeck(@RequestParam idDeck: String, idWordAndStatId: String) {
        deckService.deleteWordFromDeck(idDeck, idWordAndStatId)
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

    @PutMapping("/copyDeck")
    @Operation(description = "copyDeck")
    fun copyDeck(principal: Principal, idDeck: String) {
        deckService.copyDeck(principal, idDeck)
    }

    @GetMapping("/getAllPaging")
    fun getAllPaging(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "0") limit: Int
    ): List<DeckData> {
        return deckService.getAll(page, limit)
    }

}