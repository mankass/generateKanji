package com.example.generatekanji.config

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@SecurityScheme(
    name = "Bearer Authentication",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
class SpringdocConfig {


    @Bean
    fun webClient():GroupedOpenApi{
        return GroupedOpenApi.builder()
            .group("web-client-api")
            .pathsToMatch("/api/web-client/**")
            .build()
    }

    @Bean
    fun securityApi():GroupedOpenApi{
        return GroupedOpenApi.builder()
            .group("security")
            .pathsToMatch("/api/security/**")
            .build()
    }
}