package com.hotel.config

import org.axonframework.serialization.Serializer
import org.axonframework.serialization.json.JacksonSerializer
import org.axonframework.serialization.xml.XStreamSerializer
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class SerializerConfiguration {
    @Primary
    @Bean
    fun serializer(): Serializer {
        return XStreamSerializer
            .builder()
            .lenientDeserialization()
            .build()
    }

    @Qualifier("eventSerializer")
    @Bean
    fun eventSerializer(): Serializer {
        return JacksonSerializer.defaultSerializer()
    }
}