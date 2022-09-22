package me.jiniworld.book.model

import me.jiniworld.book.domain.entity.Tag

data class BasicModel(
    val id: Long,
    val name: String,
) {
    companion object {
        operator fun invoke(tag: Tag) =
            with(tag) {
                BasicModel(
                    id = id!!,
                    name = name
                )
            }
    }
}