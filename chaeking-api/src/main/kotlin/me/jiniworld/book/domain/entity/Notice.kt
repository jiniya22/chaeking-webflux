package me.jiniworld.book.domain.entity

import org.springframework.data.relational.core.mapping.Table

@Table("notice")
//@Where(clause = "active = 1")
class Notice(
    id: Long? = null,
    title: String,
    content: String,
): BaseBoard(id, title, content)