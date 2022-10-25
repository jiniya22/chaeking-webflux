package me.jiniworld.book.model

import me.jiniworld.book.domain.entity.Book
import me.jiniworld.book.domain.entity.BookMemoryWish
import java.time.LocalDate

data class BookSimple(
    val id: Long,
    val name: String,
    var authors: String?,
    val publisher: String? = null,
    val imageUrl: String?,
)

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
    val bookMemoryWish: BookMemoryWishContent?,
) {
    companion object {
        operator fun invoke(book: Book) =
            with(book) {
                BookDetail(
                    id = id!!,
                    name = name,
                    price = price,
                    publisher = publisher,
                    publicationDate = publicationDate,
                    isbn = isbn10,
                    imageUrl = imageUrl,
                    detailInfo = detailInfo,
                    authors = authors,
                    bookMemoryWish = bookMemoryWish?.let { BookMemoryWishContent(it) },
                )
            }
    }
}