package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column

abstract class BaseBoard(
    @Id
    open val id: Long? = null,

    @Column
    open var title: String,

    //    @Lob
    @Column
    open var content: String,

    ): BaseEntity()