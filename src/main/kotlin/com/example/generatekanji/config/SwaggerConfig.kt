package com.example.generatekanji.config

import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration



@Configuration
class SwaggerConfig {

//    @Bean
//    fun api():Docket{
//        return Docket(DocumentationType.SWAGGER_2)
//            .select()
//            .apis(RequestHandlerSelectors.basePackage("com.example.generatekanji.application.controllers.WordsController"))
//            .paths(PathSelectors.any())
//            .build()
//    }
@Bean
public  fun publicAPi(): GroupedOpenApi {
    return GroupedOpenApi.builder()
        .group("springshop-public")
        .pathsToMatch("/public/**")
        .build()


}
}