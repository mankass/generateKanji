package com.example.generatekanji.infra

import com.example.generatekanji.domain.dto.WordData
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WordRepository:CrudRepository<WordData,String>{

    fun findByWord(word:String):List<WordData>

    fun findByTranslate(word: String): List<WordData>
    fun findByOrderByWord(of: PageRequest): List<WordData>
}