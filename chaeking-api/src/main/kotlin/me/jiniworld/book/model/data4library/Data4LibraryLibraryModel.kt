package me.jiniworld.book.model.data4library

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class Data4LibraryLibrary(
    val response: Response,
) {
    @JsonNaming(value = PropertyNamingStrategies.LowerCamelCaseStrategy::class)
    data class Response(
        val pageNo: Int,
        val pageSize: Int,
        val numFound: Int,
        val resultNum: Int,
        val libs: List<Libs>,
    ) {
        data class Libs(
            val lib: Lib
        ) {
            @JsonNaming(value = PropertyNamingStrategies.LowerCamelCaseStrategy::class)
            data class Lib(
                val libCode: String,
                val libName: String,
                val address: String,
                val tel: String,
                val latitude: Double,
                val longitude: Double,
            )
        }
    }
}