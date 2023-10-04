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
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name="id", updatable = true)
    var listWords: MutableList<WordData>?,

    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(generator = "system-uuid", strategy = GenerationType.UUID)
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    val id: String?

)