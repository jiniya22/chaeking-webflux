package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("best_seller")
class BestSeller(
    @Id val id: Long?,

    val bookId: Long,
)