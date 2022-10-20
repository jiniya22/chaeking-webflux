package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("book")
class Terms(
    @Id
    val id: Long? = null,

    val title: String,

    var termsLogId: Long,
)