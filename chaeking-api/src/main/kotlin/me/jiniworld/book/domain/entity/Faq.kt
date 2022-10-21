package me.jiniworld.book.domain.entity

import org.springframework.data.relational.core.mapping.Table

@Table("faq")
class Faq(
    id: Long = 0,
    title: String,
    content: String,
): BaseBoard(id, title, content) {
    fun withId(id: Long): BaseBoard = Faq(id, title, content)
}