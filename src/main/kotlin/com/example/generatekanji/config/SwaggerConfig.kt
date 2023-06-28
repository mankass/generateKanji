package com.example.generatekanji.config

import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {

    @Bean
    public fun publicAPi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("springshop-public")
            .pathsToMatch("/public/**")
            .build()

    }
}