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
    fun generate(generatorTImeType: GeneratorTimeType, generatorType: GeneratorType): ByteArray {
        val user = userService.getByLogin("dan")

        when (generatorTImeType) {
            GeneratorTimeType.TODAY -> return filesService.createByDate(LocalDate.now(), generatorType, user)

            GeneratorTimeType.YESTERDAY -> return filesService.createByDate(
                LocalDate.now().minusDays(1),
                generatorType,
                user
            )

            GeneratorTimeType.WEEK -> filesService.createByDate(
                LocalDate.now(),
//                LocalDate.now().minusWeeks(1),
                generatorType, user
            )

            GeneratorTimeType.ALLMONTH -> filesService.createByDate(
                LocalDate.now(),
//                LocalDate.now().minusMonths(1),
                generatorType, user
            )

            GeneratorTimeType.ALL -> filesService.createALl(generatorType, user)
        }
        return TODO()
    }
}