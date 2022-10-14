package me.jiniworld.book.domain.repository

import kotlinx.coroutines.flow.Flow
import me.jiniworld.book.domain.entity.Faq
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface FaqRepository : CoroutineCrudRepository<Faq, Long> {
    fun findAllBy(pageable: Pageable): Flow<Faq>
}