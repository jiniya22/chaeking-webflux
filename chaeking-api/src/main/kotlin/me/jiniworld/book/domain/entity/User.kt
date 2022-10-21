package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("user")
data class User(
    @Id
    val id: Long = 0,

    var email: String,

    var password: String,

    var nickname: String,

    val sex: Sex = Sex.M,

    var imageUrl: String?,

    var push: Boolean = false,

    var nightPush: Boolean = false,

): BaseEntity()

enum class Sex {
    M, W
}