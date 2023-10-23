package com.example.generatekanji.domain.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
@Entity
@Schema(description = "deckData")
class DeckData(
    @Schema(description = "name")
    var name: String,


    @Schema(description = "listWords")
    @ManyToMany(cascade = [CascadeType.ALL])
    var listWords: MutableList<WordAndStat>?,

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(generator = "system-uuid", strategy = GenerationType.UUID)
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    val id: String?,

    @Schema(description = "listUsersOwners")
    @ManyToMany(cascade = [CascadeType.ALL])
    var listUsers: MutableList<UserData>?

)