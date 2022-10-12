package me.jiniworld.book.service

import me.jiniworld.book.domain.repository.NoticeRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class NoticeService(
    private val noticeRepository: NoticeRepository,
) {
    fun notices(pageable: Pageable) =
        noticeRepository.findAllBy(pageable)
//            .content
//            .map { BoardSimple(it) }

}
