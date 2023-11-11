package com.example.generatekanji.infra

import com.example.generatekanji.domain.dto.UserData
import com.example.generatekanji.domain.dto.WordAndStat
import com.example.generatekanji.domain.dto.WordData
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface WordAndStatRepository : CrudRepository<WordAndStat, String> {

    fun findByUserDataAndAndWordData(userData: UserData, wordData: WordData): WordAndStat?

    fun findByUserData(userData: UserData): List<WordAndStat>

    fun findByUserDataAndCreatedDate(userData: UserData, localDate: LocalDate): List<WordAndStat>
}

