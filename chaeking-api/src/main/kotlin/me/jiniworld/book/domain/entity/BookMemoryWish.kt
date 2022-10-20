package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("book_memory_wish")
class BookMemoryWish(
    @Id val id: Long? = null,

    val bookId: Long,

    val userId: Long,

    var memo: String,

): BaseEntity() {
    fun withId(id: Long?): BookMemoryWish = BookMemoryWish(id, bookId, userId, memo)
}