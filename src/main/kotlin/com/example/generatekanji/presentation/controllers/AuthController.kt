package com.example.generatekanji.presentation.controllers

import com.example.generatekanji.application.services.UserService
import com.example.generatekanji.application.utils.JwtUtils
import com.example.generatekanji.domain.dto.JwtRequest
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    val userService: UserService,
    val jwtUtils: JwtUtils,
    val authenticationManager: AuthenticationManager
) {

    @PostMapping("/auth")
    fun createAuthToken(@RequestBody jwt: JwtRequest): ResponseEntity<Any> {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(jwt.username, jwt.password))
        } catch (e: BadCredentialsException) {
            throw e
        }
        val userDetails = userService.loadUserByUsername(jwt.username)
        val token = jwtUtils.generatToken(userDetails)
        return ResponseEntity.ok(token)
    }

}