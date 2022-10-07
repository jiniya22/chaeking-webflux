package me.jiniworld.book.domain.entity

import org.hibernate.annotations.Where
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "faq")
@Where(clause = "active = 1")
class Faq(

): BaseBoard()