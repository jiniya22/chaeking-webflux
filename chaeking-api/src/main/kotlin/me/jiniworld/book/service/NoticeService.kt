package me.jiniworld.book.service

import me.jiniworld.book.domain.repository.NoticeRepository
import me.jiniworld.book.model.BoardDetail
import me.jiniworld.book.model.BoardSimple
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class NoticeService(
    private val noticeRepository: NoticeRepository,
) {
    fun notices(pageable: Pageable) =
        noticeRepository.findAll(pageable)
            .content
            .map { BoardSimple(it) }

    fun notice(noticeId: Long): BoardDetail {
        val notice = noticeRepository.findByIdOrNull(noticeId) ?: throw RuntimeException("조회되는 공지사항이 없습니다")
        return BoardDetail(notice)
    }
}
