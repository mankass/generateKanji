package com.example.generatekanji.domain.view

import com.example.generatekanji.domain.dto.Roles

class UserView(
    val login: String,
    val firstname: String,
    val surname: String,
    val email: String,
    val roles:  MutableList<Roles>,
    val password:String
)