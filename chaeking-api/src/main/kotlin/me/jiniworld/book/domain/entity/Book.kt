package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("book")
class Book(
    @Id val id: Long?,

    @Column
    val name: String,

    @Column
    val isbn10: String,

    @Column
    var imgUrl: String?,

    @Column
    var detailInfo: String?,

    @Column
    var link: String?,

    @Column
    val publicationDate: LocalDate?,

    /*
    val publisher: Publisher,

    val bookAndAuthors: List<BookAndAuthor>,

    val bookMemoryCompletes: List<BookMemoryComplete>,

    val bookMemoryWishes: List<BookMemoryWish>,
     */
): BaseEntity()