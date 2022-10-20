package me.jiniworld.book.model

import me.jiniworld.book.domain.entity.BaseBoard
import java.time.format.DateTimeFormatter

data class BoardCreation(
    val title: String,
    val content: String,
)

data class BoardSimple(
    val id: Long,
    val title: String,
    val createdOn: String?,
) {
    companion object {
        operator fun invoke(board: BaseBoard) =
            with(board) {
                BoardSimple(
                    id = id!!,
                    title = title,
                    createdOn = createdAt?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                )
            }
    }
}

data class BoardDetail(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: String?,
) {
    companion object {
        operator fun invoke(board: BaseBoard) =
            with(board) {
                BoardDetail(
                    id = id!!,
                    title = title,
                    content = content,
                    createdAt = createdAt?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                )
            }
    }
}