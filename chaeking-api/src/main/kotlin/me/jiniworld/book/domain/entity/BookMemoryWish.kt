package me.jiniworld.book.domain.entity

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.relational.core.mapping.Table

@Table("book_memory_wish")
class BookMemoryWish(
    @Id val id: Long?,

    val bookId: Long,

    @Transient
    @Value("null")
    val book: Book? = null,

    val userId: Long,

    var memo: String,

    ): BaseEntity()