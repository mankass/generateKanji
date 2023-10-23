package com.example.generatekanji.infra

import com.example.generatekanji.domain.dto.DeckData
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DeckRepository:CrudRepository<DeckData, String> {

}