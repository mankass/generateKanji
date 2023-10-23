package com.example.generatekanji.presentation.controllers

import com.example.generatekanji.application.services.LeaderboardService
import com.example.generatekanji.domain.view.LeaderboardUnitView
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "API")
@RequestMapping("/api/web-client/leaderBoard")
@CrossOrigin
class LeaderboardController(
    val leaderboardService: LeaderboardService
) {

    @GetMapping
    @Operation(description = "Get leaderBoard")
    fun getLeaderboard(): List<LeaderboardUnitView> {
        return leaderboardService.getTable()
    }
}