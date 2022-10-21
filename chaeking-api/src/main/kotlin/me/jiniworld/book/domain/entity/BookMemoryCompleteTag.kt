package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("book_memory_complete_tag")
data class BookMemoryCompleteTag(
    @Id val id: Long = 0,

    val bookMemoryCompleteId: Long,

    val tagId: Long,
)