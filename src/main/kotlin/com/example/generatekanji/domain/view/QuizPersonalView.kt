package com.example.generatekanji.domain.view

import com.example.generatekanji.domain.enums.TypeQuiz
import io.swagger.v3.oas.annotations.media.Schema

class QuizPersonalView(
    wordId: String,
    question: String,
    answer: String,
    hint: String,
    listOfAnswer: List<String>,
    typeQuiz: TypeQuiz,
    @Schema(description = "percentCorrect")
    percentCorrect: Int,
) : QuizView(wordId, question, answer, hint, listOfAnswer, typeQuiz) {


}