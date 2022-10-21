package me.jiniworld.book.domain.entity

import org.springframework.data.relational.core.mapping.Table

@Table("notice")
class Notice(
    id: Long = 0,
    title: String,
    content: String,
): BaseBoard(id, title, content){
    fun withId(id: Long): BaseBoard = Faq(id, title, content)
}