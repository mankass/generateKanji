package com.example.generatekanji.domain.dto

data class Page(val id:String, var words:List<Word>) {
    val WORDS_MAX_ON_PAGE=50
}