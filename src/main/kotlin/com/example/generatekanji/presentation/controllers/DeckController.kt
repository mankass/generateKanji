package com.example.generatekanji.presentation.controllers

import com.example.generatekanji.application.services.DeckService
import com.example.generatekanji.domain.dto.DeckData
import com.example.generatekanji.domain.dto.UserData
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
    fun getDeck(id:String): Optional<DeckData> {
     return   deckRepository.findById(id)
    }

    @PutMapping
    @Operation(description = "Update deck")
    fun updateDeck(){
    }

    @PostMapping
    @Operation(description = "Crate deck")
    fun createDeck(@RequestParam name: String): HttpStatus {
        deckRepository.save(DeckData("null", null, name, null))
        return HttpStatus.OK
    }

    @DeleteMapping
    @Operation(description = "Delete deck")
    fun delete(id:String){
        deckRepository.deleteById(id)
    }

    @GetMapping("/get-all")
    @Operation(description = "get all decks")
    fun getALlDecks(principal: Principal): List<DeckData> {
        println(principal)
        return deckRepository.findAll().toList()
    }

    @PutMapping("/add-word-toDeck")
    @Operation(description = "add word to deck")
    fun addWordToDeck(@RequestParam idDeck: String, @RequestParam wordId: String, @RequestParam userData: UserData) {
        deckService.addWordToDeck(idDeck, wordId, userData)
    }
}