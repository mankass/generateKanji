package com.example.generatekanji.infra

import com.example.generatekanji.domain.dto.WordAndStat
import org.springframework.data.repository.CrudRepository

interface WordAndStatRepository : CrudRepository<WordAndStat, String>