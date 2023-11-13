package com.example.generatekanji.domain.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDate
import java.util.*

@Entity
@SuppressWarnings("JpaAttributeTypeInspection")
@Schema(description = "word And Stat")
class WordAndStat(

    @Schema(description = "word")
    @ManyToOne
    val wordData: WordData,

    @Schema(description = "owner")
    @ManyToOne
    val userData: UserData,

    @Schema(description = "wrongAttempts")
    var wrongAttempts: Int,

    @Schema(description = "correctAttempts")
    var correctAttempts: Int,

    @Schema(description = "percentCorrect")
    var percentCorrect: Int,

    @CreatedDate
    @Schema(description = "createdDate")
    var createdDate: LocalDate,

    @Schema(description = "createdDate")
    var lastUsingDate: LocalDate,

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    val id: String?
)