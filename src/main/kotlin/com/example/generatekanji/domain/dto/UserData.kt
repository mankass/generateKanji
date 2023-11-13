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
@Schema(description = "userData")
class UserData(
    @Schema(description = "login")
    val login: String,

    @Schema(description = "name")
    val fisrtName: String,

    @Schema(description = "surname")
    val surname: String,

    @Schema(description = "createdData")
    @CreatedDate
    val createdData: LocalDate,

    @Schema(description = "lastLoginDate")
    val lastLogin: LocalDate,

    @Schema(description = "role User")
    val roles: MutableList<Roles>,

    @Schema(description = "email")
    val email: String,

    @Schema(description = "password")
    val password:String,

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    val id: String?

)