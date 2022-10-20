package me.jiniworld.book.model

import me.jiniworld.book.domain.entity.BookMemoryWish

data class BookMemoryWishCreation(
    val bookId: Long,
    val memo: String,
)

data class BookMemoryWishModification(
    val memo: String,
)

data class BookMemoryWishSimple(
    val id: Long,
    val bookId: Long,
    val bookName: String,
    val imageUrl: String,
)

data class BookMemoryWishContent(
    val id: Long,
    val memo: String,
) {
    companion object {
        operator fun invoke(bookMemoryWish: BookMemoryWish) =
            with(bookMemoryWish) {
                BookMemoryWishContent(
                    id = id!!,
                    memo = memo,
                )
            }
    }
}