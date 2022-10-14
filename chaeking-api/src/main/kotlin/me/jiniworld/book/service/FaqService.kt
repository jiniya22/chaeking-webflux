package me.jiniworld.book.service

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import me.jiniworld.book.config.exception.NotFoundException
import me.jiniworld.book.domain.repository.FaqRepository
import me.jiniworld.book.model.BoardDetail
import me.jiniworld.book.model.BoardSimple
import me.jiniworld.book.model.response.DataResponse
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class FaqService(
    private val faqRepository: FaqRepository,
) {
    suspend fun faqs(pageable: Pageable) =
        faqRepository.findAllBy(pageable)
            .map { BoardSimple(it) }
            .toList()
            .let { DataResponse(data = it) }


    suspend fun faq(faqId: Long) =
        faqRepository.findById(faqId)?.run { DataResponse(data = BoardDetail(this)) }
            ?: throw NotFoundException("조회되는 FAQ가 없습니다.")

}
