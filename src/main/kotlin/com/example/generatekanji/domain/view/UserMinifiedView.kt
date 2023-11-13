package com.example.generatekanji.domain.view

import com.example.generatekanji.domain.dto.UserData
import io.swagger.v3.oas.annotations.media.Schema

class UserMinifiedView(
    @Schema(description = "login")
    val login: String,
    @Schema(description = "id")
    val id: String?
) {
    constructor(userData: UserData) : this(
        userData.login, userData.id
    )
}
