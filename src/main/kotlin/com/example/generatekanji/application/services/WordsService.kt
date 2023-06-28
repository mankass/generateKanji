package com.example.generatekanji.application.services

import com.example.generatekanji.domain.dto.Level
import com.example.generatekanji.domain.dto.Word
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.util.*


@Service
class WordsService(val db: JdbcTemplate) {
    fun findWords(): List<Word> = db.query("select * from words") { response, _ ->
        Word(response.getString(1), response.getString(2),
            response.getString(3),(Level.N1))
    }
    fun save(word: Word){
        val id = word.id ?: UUID.randomUUID().toString()
        db.update("insert into words values ( ?, ?, ?, ? )",
            id, word.wordJapan,word.translateWord,word.levelN.toString())
    }
}