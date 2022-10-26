package me.jiniworld.book.model.client

import me.jiniworld.book.domain.entity.Book
import me.jiniworld.book.util.BasicUtils
import me.jiniworld.book.util.DateTimeUtils
import java.time.LocalDate

data class KakaoBookSearch(
    val query: String,
    val target: String,
    val sort: String,
    val page: Int,
    val size: Int,
)

data class KakaoBookRes(
    val documents: List<Document>,
    val meta: Meta,
) {
    data class Document(
        val title: String,
        val thumbnail: String?,
        val authors: List<String>,
        val price: Int,
        val publisher: String,
        val datetime: String?,
        val isbn: String,
        val url: String,
        val contents: String?,
    )

    data class Meta(
        val is_end: Boolean,
        val pageable_count: Int,
        val total_count: Int,
    )
}

fun KakaoBookRes.Document.toBook() = Book(
    name = title,
    price = price,
    publisher = publisher,
    authors = authors,
    publicationDate = datetime?.let {
        LocalDate.parse(it.replace(Regex("\\D"), "").substring(0,8), DateTimeUtils.FORMATTER_DATE_SIMPLE) },
    isbn13 = BasicUtils.getIsbn13ByIsbn(isbn) ?: "",
    imageUrl = thumbnail,
    link = url,
    detailInfo = contents,
)

enum class KakaoBookTarget {
    title, isbn, publisher, person;
}
enum class KakaoBookSort {
    accuracy, latest;
}