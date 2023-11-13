package com.example.generatekanji.domain.view

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "LeaderboardDeckView")
class LeaderboardDeckView(
    @Schema(description = "login")
    val deckName: String,
    @Schema(description = "usersUsing")
    val usersUsing: Int,
    @Schema(description = "idEntity")
    val idEntity: String,
    @Schema(description = "percentCorrect")
    val percentCorrect: Int,
    @Schema(description = "slot")
    var slot: Int
)