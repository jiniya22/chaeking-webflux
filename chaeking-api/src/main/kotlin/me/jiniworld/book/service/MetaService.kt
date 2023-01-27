package me.jiniworld.book.service

import me.jiniworld.book.config.exception.NotFoundException
import me.jiniworld.book.domain.entity.MetaType
import me.jiniworld.book.domain.repository.MetaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class MetaService(
    private val metaRepository: MetaRepository,
) {

    suspend fun meta(type: MetaType) =
        metaRepository.findByType(type)
            ?: throw NotFoundException("조회되는 meta 정보가 없습니다.")

}

