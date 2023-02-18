package me.jiniworld.book.model.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("chaeking")
data class ChaekingProperties(
    val secret: String,
    val data4library: Data4library,
) {
    data class Data4library(
        val url: String,
        val authKey: String,
    )
}