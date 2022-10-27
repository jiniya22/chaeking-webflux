package me.jiniworld.book.model.data4library

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class Data4LibraryRecommand(
    val response: Response,
) {
    @JsonNaming(value = PropertyNamingStrategies.LowerCamelCaseStrategy::class)
    data class Response(
        val resultNum: Int,
        val docs: List<Docs>,
    ) {
        data class Docs(
            val book: Book,
        ) {
            @JsonNaming(value = PropertyNamingStrategies.LowerCamelCaseStrategy::class)
            data class Book(
                val bookname: String,
                val authors: String,
                val publisher: String,
                val publication_year: String,
                val isbn13: String,
                val addition_symbol: String,
                val class_nm: String,
                val loan_count: String,
                @JsonProperty("bookImageURL")
                val bookImageURL: String,
            )
        }
    }
}