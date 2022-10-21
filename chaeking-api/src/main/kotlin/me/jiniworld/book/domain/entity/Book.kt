package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("book")
class Book(
    @Id val id: Long?,

    val name: String,

    var price: Int,

    val isbn10: String,

    var imageUrl: String?,

    var detailInfo: String?,

    var link: String?,

    val publicationDate: LocalDate?,

    val publisherId: Long? = null,

    @Transient
    val publisher: String? = null,

): BaseEntity()