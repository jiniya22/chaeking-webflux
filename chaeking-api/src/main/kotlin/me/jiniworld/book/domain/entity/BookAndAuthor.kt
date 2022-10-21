package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("book_and_author")
data class BookAndAuthor(
    @Id val id: Long = 0,

    val bookId: Long,

    val authorId: Long,
)