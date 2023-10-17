package com.example.generatekanji.domain.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "JwtRequest")
class JwtRequest(
    @Schema(description = "password")
    val password: String,
    @Schema(description = "username")
    val username: String
)