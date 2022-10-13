package me.jiniworld.book.domain.repository

import me.jiniworld.book.domain.entity.Faq
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface FaqRepository : ReactiveCrudRepository<Faq, Long> {
    fun findAllBy(pageable: Pageable): Flux<Faq>
}