package me.jiniworld.book.domain.repository

import me.jiniworld.book.domain.entity.Notice
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface NoticeRepository : ReactiveCrudRepository<Notice, Long> {
    fun findAllBy(pageable: Pageable): Flux<Notice>
}