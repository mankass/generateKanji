package com.example.generatekanji.application.services

import com.example.generatekanji.domain.dto.DeckData
import com.example.generatekanji.domain.dto.WordAndStat
import com.example.generatekanji.domain.view.DeckView
import com.example.generatekanji.infra.DeckRepository
import com.example.generatekanji.infra.UserRepository
import com.example.generatekanji.infra.WordRepository
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class DeckService(
    val deckRepository: DeckRepository,
    val wordRepository: WordRepository,
    val userRepository: UserRepository,
    val wordAndStatService: WordAndStatService
) {
    fun addWordToDeck(idDeck: String, wordId: String, principal: Principal) {
        val deck = deckRepository.findById(idDeck)
        val word = wordRepository.findById(wordId)
        val user = userRepository.findByLogin(principal.name)
        val wordAndStat = wordAndStatService.wordAndStatRepository.findByUserDataAndAndWordData(
            user, word.get()
        )
        if (deck.get().listUsers!!.any { it == user }) {
            if (wordAndStat != null) {
                deck.get().listWords?.add(
                    wordAndStat
                )

            } else {
                deck.get().listWords?.add(
                    WordAndStat(
                        word.get(), user,
                        0, 0, 0, null
                    )
                )
            }

        }

        deckRepository.save(deck.get())
    }

    fun getALl(principal: Principal): List<DeckView> {
        val user = userRepository.findByLogin(principal.name)
        try {
            val listDeckData = deckRepository.findByListUsersContaining(user)
            return listDeckData.map { x -> DeckView(x) }
        } catch (e: Exception) {
            throw NoSuchElementException("no user")
        }

    }

    fun createDeck(name: String, principal: Principal) {
        val user = userRepository.findByLogin(principal.name)
        deckRepository.save(DeckData(name, null, name, listUsers = mutableListOf(user)))

    }
}