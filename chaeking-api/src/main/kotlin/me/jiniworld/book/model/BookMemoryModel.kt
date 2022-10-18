package me.jiniworld.book.model

import me.jiniworld.book.domain.entity.BookMemoryWish

data class BookMemoryWishSimple(
    val id: Long,
    val bookId: Long?,
    val bookName: String?,
//    val imageUrl: String?,
) {
    companion object {
        operator fun invoke(bookMemoryWish: BookMemoryWish) =
            with(bookMemoryWish) {
                BookMemoryWishSimple(
                    id = id!!,
                    bookId = bookId,
                    bookName = book?.name,
                )
            }
    }
}