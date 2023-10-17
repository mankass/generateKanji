package com.example.generatekanji.domain.shared

import com.example.generatekanji.domain.dto.WordData

class WordAndStatShared(
    val id: String,
    val wordData: WordData,
    val wrongAttempts: Int,
    val correctAttempts: Int,
    val percentCorrect: Int
)