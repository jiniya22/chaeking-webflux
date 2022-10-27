package me.jiniworld.book.model.data4library

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class Data4LibraryHotTrend(
    val response: Response,
) {
    @JsonNaming(value = PropertyNamingStrategies.LowerCamelCaseStrategy::class)
    data class Response(
        val results: List<Results>,
    ) {
        data class Results(
            val result: Result,
        ) {
            data class Result(
                val date: String,
                val docs: List<Docs>,
            ) {
                data class Docs(
                    val doc: Doc,
                ) {
                    @JsonNaming(value = PropertyNamingStrategies.LowerCamelCaseStrategy::class)
                    data class Doc(
                        val bookname: String,
                        val authors: String,
                        val publisher: String,
                        val publication_year: String,
                        val isbn13: String,
                        val class_nm: String,
                        @JsonProperty("bookImageURL")
                        val bookImageURL: String,
                    )
                }
            }
        }

    }
}