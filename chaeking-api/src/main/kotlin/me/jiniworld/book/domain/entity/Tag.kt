package me.jiniworld.book.domain.entity

import org.hibernate.annotations.Where
import javax.persistence.*

@Table(name = "tag")
@Entity
@Where(clause = "active = 1")
class Tag(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(length = 50)
    var name: String,

): BaseEntity()