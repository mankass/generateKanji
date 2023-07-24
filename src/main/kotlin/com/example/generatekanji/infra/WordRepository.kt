package com.example.generatekanji.infra

import com.example.generatekanji.application.views.WordView
import com.example.generatekanji.domain.dto.WordData
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WordRepository:CrudRepository<WordData,Long> {

    fun fromView(wordView: WordView):WordData

}