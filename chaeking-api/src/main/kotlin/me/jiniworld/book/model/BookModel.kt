package me.jiniworld.book.model

import me.jiniworld.book.domain.entity.Book
import me.jiniworld.book.domain.entity.BookMemoryWish
import java.time.LocalDate

data class BookSimple(
    val id: Long,
    val name: String,
    var authorNames: String?,
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
//    var authors: List<String>,
    val bookMemoryWish: BookMemoryWishContent?,
) {
    companion object {
        operator fun invoke(book: Book, bookMemoryWish: BookMemoryWish?) =
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
                    bookMemoryWish = bookMemoryWish?.let { BookMemoryWishContent(it) },
                )
            }
    }
}