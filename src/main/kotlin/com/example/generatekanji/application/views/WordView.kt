package com.example.generatekanji.application.views

import io.swagger.v3.oas.annotations.media.Schema

class WordView(
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val translate: String,

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val word: String,

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    val transcription: String?
)