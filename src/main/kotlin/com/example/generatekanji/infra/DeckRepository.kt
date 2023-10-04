package com.example.generatekanji.infra

import com.example.generatekanji.domain.dto.DeckData
import org.springframework.data.repository.CrudRepository

interface DeckRepository:CrudRepository<DeckData, String> {

}