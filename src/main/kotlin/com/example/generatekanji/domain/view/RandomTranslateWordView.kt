package com.example.generatekanji.domain.view

import io.swagger.v3.oas.annotations.media.Schema
@Schema(description = "RandomTranslateWordView")
class RandomTranslateWordView(
    @Schema(description = "word")
    val word: String,
    @Schema(description = "translate")
    val correctAnswer: String,
    @Schema(description = "transcription")
    val transcription: String?,
    @Schema(description = "answers")
    val answers: List<String?>
)