package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("publisher")
data class Publisher(
    @Id val id: Long = 0,

    val name: String,

    val simpleName: String,

    @CreatedDate
    var createdAt: LocalDateTime? = null,
)