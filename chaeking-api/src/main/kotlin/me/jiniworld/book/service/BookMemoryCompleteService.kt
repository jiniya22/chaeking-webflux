package me.jiniworld.book.service

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import me.jiniworld.book.config.exception.NotFoundException
import me.jiniworld.book.domain.entity.BookMemoryComplete
import me.jiniworld.book.domain.entity.BookMemoryCompleteTag
import me.jiniworld.book.domain.repository.BookMemoryCompleteRepository
import me.jiniworld.book.domain.repository.BookMemoryCompleteTagRepository
import me.jiniworld.book.domain.repository.BookRepository
import me.jiniworld.book.model.*
import me.jiniworld.book.util.DateTimeUtils
import me.jiniworld.book.util.DescriptionUtils
import org.apache.logging.log4j.util.Strings
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime

@Transactional(readOnly = true)
@Service
class BookMemoryCompleteService(
    private val bookRepository: BookRepository,
    private val bookMemoryCompleteRepository: BookMemoryCompleteRepository,
    private val bookMemoryCompleteTagRepository: BookMemoryCompleteTagRepository,
) {
    suspend fun selectAll(userId: Long, month: String?, pageable: Pageable): DataResponse<List<BookMemoryCompleteSimple>> {
        if (Strings.isBlank(month)) {
            return bookMemoryCompleteRepository.findAllBookMemoryCompleteSimpleByUserId(userId, pageable.offset, pageable.pageSize)
            .toList()
            .let { DataResponse(data = it) }
        }
        val date: LocalDate = DateTimeUtils.getFirstDate(month!!)
        val time1: LocalDateTime = DateTimeUtils.getFirstDateTime(date)
        val time2: LocalDateTime = DateTimeUtils.getLastDateTime(date)
        return bookMemoryCompleteRepository.findAllBookMemoryCompleteSimpleByUserIdAndCreatedAtBetween(userId, time1, time2, pageable.offset, pageable.pageSize)
            .toList()
            .let { DataResponse(data = it) }
    }

    @Transactional
    suspend fun insert(userId: Long, req: BookMemoryCompleteCreation) {
        if (!bookRepository.existsById(req.bookId))
            throw NotFoundException(DescriptionUtils.INVALID_BOOK_ID)
        val bookMemoryComplete = bookMemoryCompleteRepository.findByBookIdAndUserId(req.bookId, userId)
            ?: BookMemoryComplete(bookId = req.bookId, userId = userId, memo = req.memo, rate = req.rate)

        merge(bookMemoryComplete, req)
    }

    @Transactional
    suspend fun merge(bookMemoryComplete: BookMemoryComplete, req: BookMemoryCompleteReq) =
        bookMemoryComplete
            .run {
                memo = req.memo
                rate = req.rate
                bookMemoryCompleteRepository.save(this)}
            .also {
                val bookMemoryCompleteId = it.id
                if(req.tagIds.isEmpty()) {
                    bookMemoryCompleteTagRepository.deleteAllByBookMemoryCompleteId(bookMemoryCompleteId)
                } else {
                    val oldTagIds = bookMemoryCompleteTagRepository.findAllByBookMemoryCompleteId(bookMemoryCompleteId)
                        .map { b -> b.tagId }.toList()

                    bookMemoryCompleteTagRepository.deleteAllByBookMemoryCompleteIdAndTagIdIn(
                        bookMemoryCompleteId, oldTagIds.filter { tagId -> !req.tagIds.contains(tagId) })

                    req.tagIds.filter { tagId -> !oldTagIds.contains(tagId) }.forEach { tagId ->
                        bookMemoryCompleteTagRepository.save(BookMemoryCompleteTag(bookMemoryCompleteId = bookMemoryCompleteId, tagId = tagId))
                    }
                }
            }

    suspend fun selectOne(userId: Long, bookMemoryCompleteId: Long) =
        bookMemoryCompleteRepository.findBookMemoryCompleteDetailByIdAndUserId(bookMemoryCompleteId, userId)
            ?.apply {
                tagIds = bookMemoryCompleteTagRepository.findAllByBookMemoryCompleteId(this.id).map { b -> b.tagId }.toList()
            }   // TODO tags로 변경해야함
            ?.let { DataResponse(data = it) }
            ?: throw NotFoundException(DescriptionUtils.INVALID_BOOK_MEMORY_COMPLETE)

    @Transactional
    suspend fun modify(userId: Long, bookMemoryCompleteId: Long, req: BookMemoryCompleteModification) =
        bookMemoryCompleteRepository.findByIdAndUserId(bookMemoryCompleteId, userId)
            ?.also { merge(it, req) }
            ?: throw NotFoundException(DescriptionUtils.INVALID_BOOK_MEMORY_COMPLETE)

    @Transactional
    suspend fun delete(userId: Long, bookMemoryCompleteId: Long) {
        bookMemoryCompleteRepository.deleteByIdAndUserId(bookMemoryCompleteId, userId)
    }
}