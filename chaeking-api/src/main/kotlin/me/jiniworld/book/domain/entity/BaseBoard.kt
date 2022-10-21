package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id

abstract class BaseBoard(
    @Id
    val id: Long = 0,

    var title: String,

    var content: String,

): BaseEntity()