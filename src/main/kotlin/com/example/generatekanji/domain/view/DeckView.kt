package com.example.generatekanji.domain.view

import com.example.generatekanji.domain.dto.DeckData
import com.example.generatekanji.domain.dto.WordAndStat
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "deckView")
class DeckView(

    @Schema(description = "name")
    var name: String,

    @Schema(description = "list words")
    var listWords: MutableList<WordAndStat>,

    @Schema(description = "id")
    var id: String,

    @Schema(description = "list of users")
    var listUsers: MutableList<String>
) {
    constructor(deckData: DeckData) : this(
        name = deckData.name,
        id = deckData.id!!,
        listUsers = deckData.listUsers!!.stream().map { x -> x.login }.toList(),
        listWords = deckData.listWords!!

    )
}