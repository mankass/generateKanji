package com.example.generatekanji.domain.view

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "RandomWordView")
class RandomWordView(
    @Schema(description = "word")
    val word: String,
    @Schema(description = "translate")
    val translate: String,
    @Schema(description = "transcription")
    val transcription: String?,
    @Schema(description = "answers")
    val answers: List<String?>
    )