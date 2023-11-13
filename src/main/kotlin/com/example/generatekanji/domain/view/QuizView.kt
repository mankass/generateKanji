package com.example.generatekanji.domain.view

import com.example.generatekanji.domain.enums.TypeQuiz
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "QuizUnit")
open class QuizView(

    @Schema(description = "wordId")
    val wordId: String,

    @Schema(description = "Question")
    val question: String,

    @Schema(description = "Answer")
    val answer: String,

    @Schema(description = "Hint")
    val hint: String,

    @Schema(description = "List of Answers")
    val listOfAnswer: List<String>,

    @Schema(description = "Type Quiz")
    val typeQuiz: TypeQuiz

)