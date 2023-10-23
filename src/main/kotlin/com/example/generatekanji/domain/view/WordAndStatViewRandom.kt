package com.example.generatekanji.domain.view

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "WordAndStatViewRandom")
class WordAndStatViewRandom(
    @Schema(description = "wordId")
    val id: String,

    @Schema(description = "word")
    val word: String,

    @Schema(description = "translate")
    val translate: String,

    @Schema(description = "transcription=correctAnswer")
    val correctAnswer: String?,

    @Schema(description = "answers")
    val answers: List<String?>,

    @Schema(description = "wrongAttempts")
    var wrongAttempts: Int,

    @Schema(description = "correctAttempts")
    var correctAttempts: Int,

    @Schema(description = "percentCorrect")
    var percentCorrect: Int,
)