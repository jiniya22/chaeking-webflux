package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("book_memory_wish")
class BookMemoryWish(
    @Id val id: Long?,

    val bookId: Long,

    val userId: Long,

    var memo: String,

    ): BaseEntity()