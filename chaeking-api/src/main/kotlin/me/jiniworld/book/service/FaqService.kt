package me.jiniworld.book.service

import me.jiniworld.book.domain.repository.FaqRepository
import me.jiniworld.book.model.BoardDetail
import me.jiniworld.book.model.BoardSimple
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class FaqService(
    private val faqRepository: FaqRepository,
) {
    fun faqs(pageable: Pageable) =
        faqRepository.findAll(pageable)
            .content
            .map { BoardSimple(it) }

    fun faq(faqId: Long): BoardDetail {
        val faq = faqRepository.findByIdOrNull(faqId) ?: throw RuntimeException("조회되는 공지사항이 없습니다")
        return BoardDetail(faq)
    }
}
