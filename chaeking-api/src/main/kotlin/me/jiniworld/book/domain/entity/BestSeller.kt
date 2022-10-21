package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("best_seller")
data class BestSeller(
    @Id val id: Long = 0,

    val bookId: Long,
)