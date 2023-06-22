package application.controllers

import application.services.Service
import model.Word
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class RestController(val service:Service) {


    @PostMapping("/")
    fun post(@RequestBody word: Word){
        service.save(word)

    }
}