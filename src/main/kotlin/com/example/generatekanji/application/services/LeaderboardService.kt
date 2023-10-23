package com.example.generatekanji.application.services

import com.example.generatekanji.domain.view.LeaderboardUnitView
import org.springframework.stereotype.Service


@Service
class LeaderboardService(
    val userService: UserService,
    val wordAndStatService: WordAndStatService
) {
    fun getTable(): List<LeaderboardUnitView> {
        val allUsers = userService.repository.findAll()
        val leaderboardUnitViews = mutableListOf<LeaderboardUnitView>()
        for (user in allUsers) {
            var data = 0
            var i = 0.00
            (wordAndStatService.findAllWordAndStatToUser(user)).forEach { wordAndStat -> data += wordAndStat.percentCorrect;i++ }
            leaderboardUnitViews.add(LeaderboardUnitView(user.login, (data / i).toInt()))
        }
        leaderboardUnitViews.sortByDescending { it.percentCorrect }
        return leaderboardUnitViews
    }
}