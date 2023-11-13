package com.example.generatekanji.domain.view

import com.example.generatekanji.domain.dto.UserData
import com.example.generatekanji.domain.dto.WordAndStat
import com.example.generatekanji.domain.dto.WordData
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.ManyToOne

class WordAndStatView(

    @Schema(description = "word")
    val wordData: WordData,

    @Schema(description = "owner")
    val userMinifiedView: UserMinifiedView,

    @Schema(description = "wrongAttempts")
    var wrongAttempts: Int,

    @Schema(description = "correctAttempts")
    var correctAttempts: Int,

    @Schema(description = "percentCorrect")
    var percentCorrect: Int,

    @Schema(description = "id")
    var id: String?
) {
    constructor(wordAndStat: WordAndStat) : this(
        wordAndStat.wordData, UserMinifiedView(wordAndStat.userData), wordAndStat.wrongAttempts,
        wordAndStat.correctAttempts, wordAndStat.percentCorrect, wordAndStat.id
    )

}