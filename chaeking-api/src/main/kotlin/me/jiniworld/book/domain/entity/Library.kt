package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("library")
data class Library (
    @Id
    val id: Long = 0,

    val regionCode: String,

    val code: String,

    val name: String,

    val address: String,

    val tel: String,

    val latitude: Double,

    val longitude: Double,
)