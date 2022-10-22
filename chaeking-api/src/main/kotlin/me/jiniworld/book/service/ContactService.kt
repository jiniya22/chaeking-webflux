package me.jiniworld.book.service

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import me.jiniworld.book.domain.entity.Contact
import me.jiniworld.book.domain.repository.ContactRepository
import me.jiniworld.book.model.BoardCreation
import me.jiniworld.book.model.BoardSimple
import me.jiniworld.book.model.DataResponse
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class ContactService(
    private val contactRepository: ContactRepository,
) {
    suspend fun selectAll(userId: Long, pageable: Pageable) =
        contactRepository.findAllByUserId(userId, pageable)
            .map { BoardSimple(it) }
            .toList()
            .let { DataResponse(data = it) }

    @Transactional
    suspend fun insert(userId: Long, req: BoardCreation) {
        Contact(
            title = req.title,
            content = req.content
        ).also {
            it.userId = userId      // TODO 구조 개선 가능한지 확인 필요
            contactRepository.save(it)
        }
    }

}