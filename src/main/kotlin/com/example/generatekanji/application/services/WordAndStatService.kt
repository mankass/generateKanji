package com.example.generatekanji.application.services

import com.example.generatekanji.domain.dto.UserData
import com.example.generatekanji.domain.dto.WordAndStat
import com.example.generatekanji.domain.shared.WordAndStatShared
import com.example.generatekanji.infra.WordAndStatRepository
import org.springframework.stereotype.Service

@Service
class WordAndStatService(
    val wordAndStatRepository: WordAndStatRepository
) {
    fun save(wordAndStatShared: WordAndStatShared, userData: UserData) {
        wordAndStatRepository.save(
            WordAndStat(
                wordAndStatShared.wordData, userData,
                wordAndStatShared.wrongAttempts, wordAndStatShared.correctAttempts,
                (wordAndStatShared.wrongAttempts / wordAndStatShared.correctAttempts),
                null
            )
        )

    }
}