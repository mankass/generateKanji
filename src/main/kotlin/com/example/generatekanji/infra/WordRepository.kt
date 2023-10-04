package com.example.generatekanji.infra

import com.example.generatekanji.domain.dto.WordData
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WordRepository:CrudRepository<WordData,String>