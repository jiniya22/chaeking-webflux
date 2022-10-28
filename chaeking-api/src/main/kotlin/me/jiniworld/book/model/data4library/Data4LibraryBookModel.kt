package me.jiniworld.book.model.data4library

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class Data4LibraryBookExist(
    val response: Response,
) {
    data class Response(
        val result: Result,
    ) {
        @JsonNaming(value = PropertyNamingStrategies.LowerCamelCaseStrategy::class)
        data class Result(
            val hasBook: String,
            val loanAvailable: String,
        )
    }
}