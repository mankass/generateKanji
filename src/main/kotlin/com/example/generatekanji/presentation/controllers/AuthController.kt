package com.example.generatekanji.presentation.controllers

import com.example.generatekanji.application.services.UserService
import com.example.generatekanji.application.utils.JwtUtils
import com.example.generatekanji.domain.dto.JwtRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/web-client/security")
@Tag(name = "SECURITY_API")
@CrossOrigin
class AuthController(
    val userService: UserService,
    val jwtUtils: JwtUtils,
    val authenticationManager: AuthenticationManager
) {

    @PostMapping("/auth")
    @Operation
    fun createAuthToken(@RequestParam login: String, @RequestParam password: String): ResponseEntity<String> {
        val jwt = JwtRequest(password, login)
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(jwt.username, jwt.password))
        } catch (e: BadCredentialsException) {
            throw e
        }
        val userDetails = userService.loadUserByUsername(jwt.username)
        val token = jwtUtils.generateToken(userDetails)
        return ResponseEntity.ok(token)
    }

}