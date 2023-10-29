package com.example.generatekanji.domain.enums

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "TypeQuiz")
enum class TypeQuiz {

    @Schema(description = "TRANSLATE")
    TRANSLATE,

    @Schema(description = "KANJI")
    KANJI,

    @Schema(description = "TRANSCRIPTION")
    TRANSCRIPTION,

    @Schema(description = "PERSONAL")
    PERSONAL
}