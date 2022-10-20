package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("book_memory_complete_tag")
class BookMemoryCompleteTag(
    @Id val id: Long?,

    val bookMemoryCompleteId: Long,

    val tagId: Long,
)