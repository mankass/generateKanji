package application.services

import model.Word
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class Service(val db:JdbcTemplate) {


    fun save(word:Word){
        val id=word.id ?: UUID.randomUUID().toString()
        db.update("insert into messages values ( ?, ? )",
            word.translate, word.word,word.level)
    }
}