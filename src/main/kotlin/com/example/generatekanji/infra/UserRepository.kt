package com.example.generatekanji.infra

import com.example.generatekanji.domain.dto.UserData
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository:CrudRepository<UserData,String> {
    fun findByLogin(login: String?): UserData?


}