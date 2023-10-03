package com.example.generatekanji.presentation.controllers

import com.example.generatekanji.application.services.SecurityService
import com.example.generatekanji.domain.dto.UserData
import com.example.generatekanji.domain.view.UserView
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@CrossOrigin
@RequestMapping("api/security")
@Tag(name = "SECURITY API")
class SecurityController(
    val securityService: SecurityService
) {


    @GetMapping("/user")
    @Operation(description = "Get user by id")
    fun getUserById(@RequestParam id: String): Optional<UserData> {
        return securityService.getUser(id)
    }

    @PostMapping("/user")
    @Operation(description = "Create user")
    fun createUser(@RequestBody user: UserView): String? {
        try {
            return securityService.createUser(user)
        } catch (e: IllegalArgumentException){
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,"login"
            )
        }
    }

    @GetMapping("/getALl")
    @Operation(description = "getAllUsers")
    fun getAllUsers(): MutableIterable<UserData> {
        return securityService.getAll()
    }


    @PutMapping("/checkLogin")
    @Operation(description ="check for existing Login" )
    fun checkLogin(@RequestParam login:String):Boolean{
       return securityService.findIsExistingLogin(login)
    }


    @DeleteMapping("/user")
    @Operation(description = "delete User")
    fun deleteUser(@RequestParam id:String){
        securityService.deleteUser(id)
    }


}