package com.example.generatekanji.application.services

import com.example.generatekanji.domain.enums.GeneratorTimeType
import com.example.generatekanji.domain.enums.GeneratorType
import org.springframework.stereotype.Service
import java.security.Principal
import java.time.LocalDate

@Service
class GeneratorService(
    val filesService: FilesService,
    val userService: UserService

) {
    fun generate(
        generatorTImeType: GeneratorTimeType,
        generatorType: GeneratorType
    ) {
        val user = userService.getByLogin("dan")

        when (generatorTImeType) {
            GeneratorTimeType.TODAY -> filesService.createByDate(LocalDate.now(), generatorType, user)
            GeneratorTimeType.YESTERDAY -> TODO()
            GeneratorTimeType.WEEK -> TODO()
            GeneratorTimeType.ALLMONTH -> TODO()
            GeneratorTimeType.ALL -> TODO()
        }
    }
}