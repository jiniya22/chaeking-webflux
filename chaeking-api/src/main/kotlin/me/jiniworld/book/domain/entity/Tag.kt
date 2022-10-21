package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("tag")
data class Tag(
    @Id
    val id: Long = 0,

    var name: String,

): BaseEntity()