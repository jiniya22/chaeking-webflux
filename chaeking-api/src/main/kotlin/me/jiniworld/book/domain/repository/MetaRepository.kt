package me.jiniworld.book.domain.repository

import me.jiniworld.book.domain.entity.Meta
import me.jiniworld.book.domain.entity.MetaType
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface MetaRepository : ReactiveCrudRepository<Meta, Long> {
    fun findByType(type: MetaType): Mono<Meta>
}