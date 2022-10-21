package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("book")
data class Terms(
    @Id
    val id: Long = 0,

    val title: String,

    var termsLogId: Long,
)