package com.example.generatekanji.application.services

import com.example.generatekanji.domain.dto.UserData
import com.example.generatekanji.domain.view.UserView
import com.example.generatekanji.infra.UserRepository
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.util.*

@Service
class SecurityService(
    val repository: UserRepository
)  {
    fun getUser(id: String): Optional<UserData> {
        return repository.findById(id)
    }

    fun createUser(user: UserView): String? {
        if (findIsExistingLogin(user.login)) {
            val newUser = UserData(
                user.login,
                user.firstname,
                user.surname,
                LocalDate.now(),
                LocalDate.now(),
                user.roles,
                user.email,
                user.password,
                null
            )
            val id = repository.save(newUser)
            return id.id;
        }
        throw IllegalArgumentException("Логин занят")
    }

    fun getAll(): MutableIterable<UserData> {
        return repository.findAll()
    }

    fun findIsExistingLogin(string: String): Boolean {
        return try {
            var temp = repository.findByLogin(string)
            false
        }catch (e:Exception){
            true
        }
    }

    fun deleteUser(id: String) {
        repository.deleteById(id)
    }
}