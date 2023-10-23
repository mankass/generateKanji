package com.example.generatekanji.presentation.controllers

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal


@RestController
@Tag(name = "API")
@RequestMapping("/api/web-client/utils")
@CrossOrigin
class UtilController {

    @GetMapping("/whoAmI")
    @Operation(description = "Who Am i")
    fun whoAmI(principal: Principal): String? {
        return principal.name
    }
}