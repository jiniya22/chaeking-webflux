package me.jiniworld.book.model.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("book-search")
data class BookSearchProperties(
    val kakao: Kakao,
    val naver: Naver,
) {
    data class Kakao(
        val apiKey: String,
    )
    data class Naver(
        val clientId: String,
        val clientSecret: String,
    )
}