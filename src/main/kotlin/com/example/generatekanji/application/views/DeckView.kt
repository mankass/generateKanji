package com.example.generatekanji.application.views;


import com.example.generatekanji.domain.dto.WordData
import io.swagger.v3.oas.annotations.media.Schema

class DeckView(

@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
val name: String,

@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
val word: String,

@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
val listWords: List<WordData>
)