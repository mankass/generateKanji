package com.example.generatekanji.application.services

import com.example.generatekanji.domain.dto.UserData
import com.example.generatekanji.domain.dto.WordAndStat
import com.example.generatekanji.infra.DeckRepository
import com.example.generatekanji.infra.WordRepository
import org.springframework.stereotype.Service

@Service
class DeckService(
    val deckRepository: DeckRepository,
    val wordRepository: WordRepository
) {
    fun addWordToDeck(idDeck: String, wordId: String, userData: UserData) {
        val deck = deckRepository.findById(idDeck)
        val word = wordRepository.findById(wordId)
        deck.get().listWords?.add(
            WordAndStat(
                word.get(), userData,
                0, 0, 0, null
            )
        )
        deckRepository.save(deck.get())
    }
}