package me.jiniworld.book.service

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactor.awaitSingleOrNull
import me.jiniworld.book.config.exception.NotFoundException
import me.jiniworld.book.domain.entity.BookMemoryComplete
import me.jiniworld.book.domain.repository.BookMemoryCompleteRepository
import me.jiniworld.book.domain.repository.BookRepository
import me.jiniworld.book.model.BookMemoryCompleteCreation
import me.jiniworld.book.model.BookMemoryCompleteModification
import me.jiniworld.book.model.BookMemoryCompleteSimple
import me.jiniworld.book.model.DataResponse
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
                .run {
                    memo = req.memo
                    rate = req.rate
                    bookMemoryCompleteRepository.save(this)}
        // TODO book_memory_complete_tag insert
    }

    suspend fun selectOne(userId: Long, bookMemoryCompleteId: Long) =
        bookMemoryCompleteRepository.findBookMemoryCompleteDetailByIdAndUserId(bookMemoryCompleteId, userId)
            ?.let { DataResponse(data = it) }
            ?: throw NotFoundException(DescriptionUtils.INVALID_BOOK_MEMORY_COMPLETE)

    @Transactional
    suspend fun modify(userId: Long, bookMemoryCompleteId: Long, req: BookMemoryCompleteModification) {
        bookMemoryCompleteRepository.findByIdAndUserId(bookMemoryCompleteId, userId)
            ?.apply {
                memo = req.memo
                bookMemoryCompleteRepository.save(this)
            } ?: throw NotFoundException(DescriptionUtils.INVALID_BOOK_MEMORY_COMPLETE)
    }

    @Transactional
    suspend fun delete(userId: Long, bookMemoryCompleteId: Long) {
        bookMemoryCompleteRepository.deleteByIdAndUserId(bookMemoryCompleteId, userId)
    }
}