package com.example.generatekanji.application.services

import com.example.generatekanji.infra.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
class UserService(val repository: UserRepository) :UserDetailsService
{
    @Transactional
    override fun loadUserByUsername(username: String?): UserDetails {

        val user= repository.findByLogin(username)
        return User(user.login,user.password,user.roles.stream().map { role->SimpleGrantedAuthority(role.name) }.collect(Collectors.toList()))

    }
}