package com.example.generatekanji.application.services

import com.example.generatekanji.domain.view.LeaderboardDeckView
import com.example.generatekanji.domain.view.LeaderboardUnitView
import org.springframework.stereotype.Service


@Service
class LeaderboardService(
    val userService: UserService,
    val wordAndStatService: WordAndStatService,
    val deckService: DeckService
) {
    fun getTable(): List<LeaderboardUnitView> {
        val allUsers = userService.repository.findAll()
        val leaderboardUnitViews = mutableListOf<LeaderboardUnitView>()
        var index = 1
        for (user in allUsers) {
            var data = 0
            var i = 0.00
            (wordAndStatService.findAllWordAndStatToUser(user)).forEach { wordAndStat -> data += wordAndStat.percentCorrect;i++ }

            leaderboardUnitViews.add(LeaderboardUnitView(user.login, user.id.orEmpty(), (data / i).toInt(), 0))
        }
        leaderboardUnitViews.sortByDescending { it.percentCorrect }
        leaderboardUnitViews.forEach { it.slot = index;index++ }
        return leaderboardUnitViews
    }

    fun getDeckLeaderboard(): List<LeaderboardDeckView> {
        val allDecks = deckService.getAll()
        var index = 1
        val leaderboardUnitViews = mutableListOf<LeaderboardDeckView>()
        for (deck in allDecks) {
            var data = 0
            deck.listWords?.forEach { wordAndStat -> data += wordAndStat.percentCorrect }
            var percentCorrect = 0
            if (deck.listWords?.size != 0) {
                percentCorrect = data / deck.listWords!!.size.or(0)
            }
            if (!deck.isPersonal) {
                leaderboardUnitViews.add(
                    LeaderboardDeckView(
                        deck.name,
                        deck.listUsers?.size!!,
                        deck.id.orEmpty(),
                        percentCorrect,
                        0
                    )
                )
            }


        }
        leaderboardUnitViews.sortByDescending { it.usersUsing }
        leaderboardUnitViews.forEach { it.slot = index;index++ }
        return leaderboardUnitViews

    }
}