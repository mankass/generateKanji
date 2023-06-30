package com.example.generatekanji.application.kafka.configuration

import org.apache.kafka.clients.admin.AdminClientConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin


@Configuration
class KafkaTopicConfig {


    @Value("\${spring.kafka.bootstrap-servers}")
    private lateinit var  bootstrapAddress:Object

    @Bean
    fun KafkaAdmin():KafkaAdmin{
        var configs= mutableMapOf<String?,Any>()
        configs[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        return org.springframework.kafka.core.KafkaAdmin(configs)
    }
//    @Bean
//    fun newTopic(): Topc

}