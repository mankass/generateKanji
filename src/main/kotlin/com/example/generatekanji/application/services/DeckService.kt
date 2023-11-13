package com.example.generatekanji.application.services

import com.example.generatekanji.domain.dto.DeckData
import com.example.generatekanji.domain.dto.WordAndStat
import com.example.generatekanji.domain.view.DeckView
import com.example.generatekanji.infra.DeckRepository
import com.example.generatekanji.infra.UserRepository
import com.example.generatekanji.infra.WordRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.security.Principal
import java.time.LocalDate
import java.util.*
import kotlin.NoSuchElementException

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
        val wordAndStat = wordAndStatService.wordAndStatRepository.findByUserDataAndWordData(
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
                        word.get(), user, 0, 0, 0,
                        LocalDate.now(), LocalDate.now(), null
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

    fun createDeck(name: String, principal: Principal): String? {
        val user = userRepository.findByLogin(principal.name)
        val id = deckRepository.save(DeckData(name, false, null, null, listUsers = mutableListOf(user)))
        return id.id
    }

    fun updateDeck(idDeck: String) {

    }

    fun deleteUserFromDeck(login: String, deckId: String) {
        val deck = deckRepository.findById(deckId)
        deck.get().listUsers!!.removeAll { item -> item.login == login }
        deckRepository.save(deck.get())

    }

    fun addUserToDeck(login: String, deckId: String) {
        val deck = deckRepository.findById(deckId)
        val user = userRepository.findByLogin(login)
        deck.get().listUsers!!.add(user)
        deckRepository.save(deck.get())
    }

    fun getAll(): List<DeckData> {
        return deckRepository.findAll().toList()
    }

    fun deleteWordFromDeck(idDeck: String, idWordAndStatId: String) {
        val deck = deckRepository.findById(idDeck)

        deck.get().listWords?.removeAll { x -> x.id.equals(idWordAndStatId) }
        deckRepository.save(deck.get())

    }

    fun copyDeck(principal: Principal, idDeck: String) {
        val deck = deckRepository.findById(idDeck).get()
        val user = userRepository.findByLogin(principal.name)
        deckRepository.save(DeckData(deck.name, false, deck.listWords, null, mutableListOf(user)))

    }

    fun getAll(page: Int, limit: Int): List<DeckData> {
        return deckRepository.findByOrderByName(PageRequest.of(page, limit))
    }

    fun findById(id: String): Optional<DeckData> {
        return deckRepository.findById(id)
    }
}
