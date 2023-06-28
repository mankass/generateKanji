package com.example.generatekanji.application.services

import com.example.generatekanji.domain.dto.Page
import java.util.UUID


class PageController() {

    val wordsServices: WordsService = TODO()

    fun save(): Page {
        val id = UUID.randomUUID().toString()
        val listALlWords = wordsServices.findWords()
        listALlWords.size;
        val result: Page = Page(id, listALlWords)
        result.words = listALlWords

        return result
    }

}