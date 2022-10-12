package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("tag")
//@Where(clause = "active = 1")
class Tag(
    @Id
    val id: Long? = null,

    @Column
    var name: String,

): BaseEntity()