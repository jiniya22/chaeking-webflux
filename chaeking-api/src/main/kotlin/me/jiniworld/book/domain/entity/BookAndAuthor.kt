package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("book_and_author")
class BookAndAuthor(
    @Id val id: Long?,

    val bookId: Long,

    val authorId: Long,
)