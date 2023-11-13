package com.example.generatekanji.application.services

import com.example.generatekanji.domain.enums.GeneratorTimeType
import com.example.generatekanji.domain.enums.GeneratorType
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.Data
import org.springframework.stereotype.Service
import java.lang.Error
import java.time.LocalDate

@Service
class GeneratorService(
    val filesService: FilesService,
    val userService: UserService

) {
    fun generate(generatorTImeType: GeneratorTimeType, generatorType: GeneratorType, login: String): ByteArray {

        val user = userService.getByLogin("dan")
//        val user = userService.getByLogin(login) TODO after add auth to swagger

        when (generatorTImeType) {
            GeneratorTimeType.TODAY -> return filesService.createByDate(LocalDate.now(), generatorType, user)

            GeneratorTimeType.YESTERDAY -> return filesService.createByDate(
                LocalDate.now().minusDays(1),
                LocalDate.now(),
                generatorType,
                user
            )

            GeneratorTimeType.WEEK -> return filesService.createByDate(
                LocalDate.now().minusWeeks(1),
                LocalDate.now(),
                generatorType, user
            )

            GeneratorTimeType.ALLMONTH -> return filesService.createByDate(
                LocalDate.now().minusMonths(1),
                LocalDate.now(),
                generatorType, user
            )

            GeneratorTimeType.ALL -> return filesService.createALl(generatorType, user)
        }
        return TODO()
    }
}