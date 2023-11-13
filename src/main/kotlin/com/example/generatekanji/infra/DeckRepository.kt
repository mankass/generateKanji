package com.example.generatekanji.infra

import com.example.generatekanji.domain.dto.DeckData
import com.example.generatekanji.domain.dto.UserData
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DeckRepository : CrudRepository<DeckData, String> {

    fun findByListUsersContaining(userData: UserData): List<DeckData>

    fun findByOrderByName(pageable: Pageable): List<DeckData>


}