package com.example.generatekanji.domain.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import javax.ws.rs.DefaultValue

@Entity
@Schema(description = "deckData")
class DeckData(
    @Schema(description = "name")
    var name: String,

    @Schema(description = "isPersonal")
    val isPersonal: Boolean,

    @Schema(description = "listWords")
    @ManyToMany(cascade = [CascadeType.ALL])
    var listWords: MutableList<WordAndStat>?,

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(generator = "system-uuid", strategy = GenerationType.UUID)
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    val id: String?,

    @Schema(description = "listUsersOwners")
    @ManyToMany(cascade = [CascadeType.DETACH], fetch = FetchType.EAGER)
    var listUsers: MutableList<UserData>?

)