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
    var wrongAttempts: Int,

    @Schema(description = "correctAttempts")
    var correctAttempts: Int,

    @Schema(description = "percentCorrect")
    var percentCorrect: Int,

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    val id: String?
)