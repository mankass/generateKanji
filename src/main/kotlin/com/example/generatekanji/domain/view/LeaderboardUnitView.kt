package com.example.generatekanji.domain.view

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "LeaderboardUnit")
class LeaderboardUnitView(
    @Schema(description = "login")
    val login: String,
    @Schema(description = "idEntity")
    val idEntity: String,
    @Schema(description = "percentCorrect")
    val percentCorrect: Int,
    @Schema(description = "slot")
    var slot: Int
)