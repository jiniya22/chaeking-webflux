package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column

abstract class BaseBoard(
    @Id
    val id: Long? = null,

    @Column
    var title: String,

    @Column
    var content: String,

): BaseEntity()