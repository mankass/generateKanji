package com.example.generatekanji.domain.view

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "MinifiedUserView")
class MinifiedUserView(
    @Schema(description = "login")
    val login: String,

    @Schema(description = "name")
    val fisrtName: String,

    @Schema(description = "surname")
    val surname: String,

    @Schema(description = "email")
    val email: String,

    @Schema(description = "id")
    val id: String
)