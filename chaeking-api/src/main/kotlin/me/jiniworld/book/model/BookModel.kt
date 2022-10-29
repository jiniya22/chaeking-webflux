package me.jiniworld.book.model

import me.jiniworld.book.domain.entity.Book
import java.time.LocalDate

data class BookSimple(
    val id: Long,
    val name: String,
    var authors: String? = null,
    val publisher: String? = null,
    val imageUrl: String?,
) {
    companion object {
        operator fun invoke(book: Book) {
            with(book) {
                BookSimple(
                    id = id!!,
                    name = name,
                    authors = authors?.joinToString(","),
                    publisher = publisher,
                    imageUrl = imageUrl,
                )
            }
        }
    }
}

data class BookDetail(
    val id: Long,
    val name: String,
    var price: Int,
    val publisher: String?,
    val publicationDate: LocalDate?,
    val isbn: String,
    var imageUrl: String?,
    var detailInfo: String?,
    var authors: List<String>?,
    var bookMemoryWish: BookMemoryWishContent?,
    var libraryInfo: LibraryInfo?,
) {
    data class LibraryInfo(
        val id: Long,
        val name: String,
        val code: String,
        val hasBook: String,
        val loanAvailable: String,
    )
}