package me.jiniworld.book.domain.entity

import org.springframework.data.relational.core.mapping.Table

@Table("faq")
class Faq(
    id: Long? = null,
    title: String,
    content: String,
): BaseBoard(id, title, content)