package com.example.generatekanji.domain.view

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "QuizUnit")
class QuizView(

    @Schema(description = "Question")
    val question: String,

    @Schema(description = "Answer")
    val answer: String,

    @Schema(description = "Hint")
    val hint: String,

    @Schema(description = "List of Answers")
    val listOfAnswer: List<String>

)