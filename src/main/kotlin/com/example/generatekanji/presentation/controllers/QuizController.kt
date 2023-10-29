package com.example.generatekanji.presentation.controllers

import com.example.generatekanji.application.services.QuizService
import com.example.generatekanji.domain.view.QuizView
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/web-client/quiz")
@CrossOrigin
@Tag(name = "API_QUIZ", description = "QuizApi")
class QuizController(
    val quizService: QuizService
) {

    @Operation(description = "")
    @GetMapping("/translate-quiz")
    fun getTranslateQuiz(): QuizView {
        return quizService.getTranslateQuiz()
    }

    @Operation(description = "")
    @GetMapping("/transcription-quiz")
    fun getTranscriptionQuiz(): QuizView {
        return quizService.getTranscriptionQuiz()
    }

    @Operation(description = "")
    @GetMapping("/kanji-quiz")
    fun getKanjiQuiz(): QuizView {
        return quizService.getKanjiQuiz()
    }

}