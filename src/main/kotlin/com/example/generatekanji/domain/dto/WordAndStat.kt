package com.example.generatekanji.domain.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
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
    val wrongAttempts: Int?,

    @Schema(description = "correctAttempts")
    val correctAttempts: Int,

    @Schema(description = "percentCorrect")
    val percentCorrect: Int,

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    val id: String?
)