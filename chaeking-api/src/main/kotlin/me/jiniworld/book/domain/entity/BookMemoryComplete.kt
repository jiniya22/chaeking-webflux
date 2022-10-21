package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("book_memory_complete")
data class BookMemoryComplete(
    @Id val id: Long = 0,

    private val bookId: Long,

    private val userId: Long,

    var memo: String,

    var rate: Double,

): BaseEntity()