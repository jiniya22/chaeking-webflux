package me.jiniworld.book.service

import me.jiniworld.book.config.exception.NotFoundException
import me.jiniworld.book.domain.entity.Meta
import me.jiniworld.book.domain.entity.MetaType
import me.jiniworld.book.domain.repository.MetaRepository
import me.jiniworld.book.model.response.DataResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Transactional(readOnly = true)
@Service
class MetaService(
    private val metaRepository: MetaRepository,
) {

    suspend fun meta(type: MetaType) =
        metaRepository.findByType(type)?.run { DataResponse(data = this.content) }
            ?:  throw NotFoundException("조회되는 meta 정보가 없습니다.")


}

