package me.jiniworld.book.service

import kotlinx.coroutines.flow.*
import me.jiniworld.book.config.exception.NotFoundException
import me.jiniworld.book.domain.repository.NoticeRepository
import me.jiniworld.book.model.BoardDetail
import me.jiniworld.book.model.BoardSimple
import me.jiniworld.book.model.response.DataResponse
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class NoticeService(
    private val noticeRepository: NoticeRepository,
) {
    suspend fun notices(pageable: Pageable) =
        noticeRepository.findAllBy(pageable)
            .map { BoardSimple(it) }
            .toList()
            .let { DataResponse(data = it) }

    suspend fun notice(id: Long) =
        noticeRepository.findById(id)?.run { DataResponse(data = BoardDetail(this)) }
            ?: throw NotFoundException("조회되는 공지사항이 없습니다.")

}
