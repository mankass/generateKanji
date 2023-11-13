package com.example.generatekanji.infra

import com.example.generatekanji.domain.dto.UserData
import com.example.generatekanji.domain.dto.WordAndStat
import com.example.generatekanji.domain.dto.WordData
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface WordAndStatRepository : CrudRepository<WordAndStat, String> {

    fun findByCreatedDateAndUserDataOrderByLastUsingDate(
        dateAfter: LocalDate,
        userData: UserData
    ): List<WordAndStat>

    fun findByUserDataAndAndWordData(userData: UserData, wordData: WordData): WordAndStat?

    fun findByCreatedDateBetweenAndUserDataOrderByLastUsingDate(
        dateStart: LocalDate,
        dateEnd: LocalDate,
        userData: UserData
    ): List<WordAndStat>

    fun findByUserData(userData: UserData): List<WordAndStat>

    fun findByCreatedDateBetween(dateStart: LocalDate, dateEnd: LocalDate): List<WordAndStat>

    fun findByUserDataAndCreatedDate(userData: UserData, localDate: LocalDate): List<WordAndStat>
}

