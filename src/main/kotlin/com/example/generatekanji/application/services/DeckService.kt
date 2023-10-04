package com.example.generatekanji.application.services

import com.example.generatekanji.infra.DeckRepository
import com.example.generatekanji.infra.WordRepository
import org.springframework.stereotype.Service

@Service
class DeckService(
    val deckRepository: DeckRepository,
    val wordRepository: WordRepository
) {
    fun addWordToDeck(idDeck: String, wordId: String) {
        //   deck.listWords = []
        var deck = deckRepository.findById(idDeck)
        val word = wordRepository.findById(wordId)
        deck.get().listWords?.add(word.get())

        val result = deckRepository.save(deck.get())
    }
}