package com.example.generatekanji.domain.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDate

@Entity
@Schema(description = "wordData")
class WordData(

    @Schema(description = "word")
    val word: String,

    @Schema(description = "translate")
    val translate: String,

    @Schema(description = "createdData")
    @CreatedDate
    val createdData: LocalDate,

    @Schema(description = "transcription")
    val transcription: String?,

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    val id: String
)
