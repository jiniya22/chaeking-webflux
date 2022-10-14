package me.jiniworld.book.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Component
class WebConfig {

    @Bean
    fun objectMapper(): JsonMapper =
        JsonMapper.builder()
            .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .enable(SerializationFeature.INDENT_OUTPUT)
            .enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
            .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
            .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING)
            .disable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .addModule(
                KotlinModule.Builder()
                    .withReflectionCacheSize(512)
                    .configure(KotlinFeature.NullToEmptyCollection, false)
                    .configure(KotlinFeature.NullToEmptyMap, false)
                    .configure(KotlinFeature.NullIsSameAsDefault, false)
                    .configure(KotlinFeature.SingletonSupport, true)
                    .configure(KotlinFeature.StrictNullChecks, false)
                    .build()
            )
            .addModule(createDateModule())
            .build()

    private fun createDateModule(): SimpleModule = with(SimpleModule()) {
        addSerializer(LocalDate::class.java, LocalDateSerializer())
        addDeserializer(LocalDate::class.java, LocalDateDeserializer())
        addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer())
        addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer())
        addSerializer(LocalTime::class.java, LocalTimeSerializer())
        addDeserializer(LocalTime::class.java, LocalTimeDeserializer())
    }

//    @Bean
//    fun modelResolver(@Qualifier("jsonMapper") jsonMapper: ObjectMapper?): ModelResolver? {
//        return ModelResolver(jsonMapper)
//    }
}