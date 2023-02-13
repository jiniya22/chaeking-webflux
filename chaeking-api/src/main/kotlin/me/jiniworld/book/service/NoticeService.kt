package me.jiniworld.book.service

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import me.jiniworld.book.config.exception.NotFoundException
import me.jiniworld.book.domain.repository.NoticeRepository
import me.jiniworld.book.model.BoardSimple
import me.jiniworld.book.model.PagingRequest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class NoticeService(
    private val noticeRepository: NoticeRepository,
) {
    suspend fun notices(req: PagingRequest) =
        noticeRepository.findAllBy(PageRequest.of(req.page, req.size, Sort.by(Sort.Order.desc("id"))))
            .map { BoardSimple(it) }
            .toList()

    suspend fun notice(id: Long) =
        noticeRepository.findById(id)
            ?: throw NotFoundException("조회되는 공지사항이 없습니다.")

}
