package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("book_memory_complete")
class BookMemoryComplete(
    @Id val id: Long? = null,

    private val bookId: Long,

    private val userId: Long,

    var memo: String,

    var rate: Double,

): BaseEntity() {
    fun withId(id: Long?): BookMemoryComplete = BookMemoryComplete(id, bookId, userId, memo, rate)
}