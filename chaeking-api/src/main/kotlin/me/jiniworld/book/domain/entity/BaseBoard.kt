package me.jiniworld.book.domain.entity

import javax.persistence.*

@MappedSuperclass
abstract class BaseBoard: BaseEntity(){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(length = 100)
    lateinit var title: String

    @Lob
    lateinit var content: String
}