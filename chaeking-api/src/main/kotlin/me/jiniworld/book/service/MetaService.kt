package me.jiniworld.book.service

import me.jiniworld.book.domain.entity.Meta
import me.jiniworld.book.domain.entity.MetaType
import me.jiniworld.book.domain.repository.MetaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Transactional(readOnly = true)
@Service
class MetaService(
    private val metaRepository: MetaRepository,
) {

    fun meta(type: MetaType): Mono<String> {
        return metaRepository.findByType(type).map{ it.content }
    }

}

