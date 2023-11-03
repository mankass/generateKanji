package com.example.generatekanji.presentation.controllers

import com.example.generatekanji.application.services.QuizService
import com.example.generatekanji.domain.enums.TypeQuiz
import com.example.generatekanji.domain.view.QuizView
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("api/web-client/quiz")
@CrossOrigin
@Tag(name = "API_QUIZ", description = "QuizApi")
class QuizController(
    val quizService: QuizService
) {

    @Operation(description = "getByType")
    @GetMapping("/get-by-type")
    fun getQuizByType(@RequestParam quizType: TypeQuiz): QuizView {
        //TODO fix !!not null

        return quizService.getQuizByType(quizType)!!
    }

    @Operation(description = "createByQuiz")
    @PutMapping("createFromQuiz")
    fun createFromQuiz(@RequestParam quizView: QuizView, principal: Principal) {
        quizService.createFromQuiz(quizView, principal)
    }

}