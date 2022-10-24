package me.jiniworld.book.domain.entity

import org.springframework.data.relational.core.mapping.Table

@Table("contact")
class Contact(
    id: Long = 0,
    title: String,
    content: String,
): BaseBoard(id, title, content) {
    var userId: Long? = null
    var answerTitle: String? = null
    var answerContent: String? = null

    fun withId(id: Long): BaseBoard = Contact(id, title, content)

}