package com.example.generatekanji.domain.shared

import com.example.generatekanji.domain.dto.WordData
import com.example.generatekanji.domain.enums.AnswerStatus
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "WordAndStatShared")
class WordAndStatShared(
    @Schema(description = "answerStatus")
    val answerStatus: AnswerStatus,
    @Schema(description = "wordData")
    val wordData: WordData,
    @Schema(description = "wrongAttempts")
    val wrongAttempts: Int,
    @Schema(description = "correctAttempts")
    var correctAttempts: Int,
    @Schema(description = "percentCorrect")
    val percentCorrect: Int
)