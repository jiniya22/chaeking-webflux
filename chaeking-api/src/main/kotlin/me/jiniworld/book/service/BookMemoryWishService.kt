package me.jiniworld.book.service

import kotlinx.coroutines.flow.toList
import me.jiniworld.book.config.exception.NotFoundException
import me.jiniworld.book.domain.entity.BookMemoryWish
import me.jiniworld.book.domain.repository.BookMemoryWishRepository
import me.jiniworld.book.domain.repository.BookRepository
import me.jiniworld.book.model.BookMemoryWishCreation
import me.jiniworld.book.model.BookMemoryWishModification
import me.jiniworld.book.model.BookMemoryWishSimple
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
class BookMemoryWishService(
    private val bookRepository: BookRepository,
    private val bookMemoryWishRepository: BookMemoryWishRepository,
) {
    suspend fun selectAll(userId: Long, month: String?, pageable: Pageable): DataResponse<List<BookMemoryWishSimple>> {
        if (Strings.isBlank(month)) {
            return bookMemoryWishRepository.findAllBookMemoryWishSimpleByUserId(userId, pageable.offset, pageable.pageSize)
            .toList()
            .let{ DataResponse(data = it)}
        }
        val date: LocalDate = DateTimeUtils.getFirstDate(month!!)
        val time1: LocalDateTime = DateTimeUtils.getFirstDateTime(date)
        val time2: LocalDateTime = DateTimeUtils.getLastDateTime(date)
        return bookMemoryWishRepository.findAllBookMemoryWishSimpleByUserIdAndCreatedAtBetween(userId, time1, time2, pageable.offset, pageable.pageSize)
            .toList()
            .let { DataResponse(data = it) }
    }

    @Transactional
    suspend fun insert(userId: Long, req: BookMemoryWishCreation) {
        if (!bookRepository.existsById(req.bookId))
            throw NotFoundException(DescriptionUtils.INVALID_BOOK_ID)

        bookMemoryWishRepository.findByBookIdAndUserId(req.bookId, userId)
            ?: BookMemoryWish(bookId = req.bookId, userId = userId, memo = req.memo)
                .apply {
                    memo = req.memo
                    bookMemoryWishRepository.save(this)
                }
    }

    suspend fun selectOne(userId: Long, bookMemoryWishId: Long) =
        bookMemoryWishRepository.findBookMemoryWishDetailByIdAndUserId(bookMemoryWishId, userId)
            ?.let { DataResponse(data = it) }
            ?: throw NotFoundException(DescriptionUtils.INVALID_BOOK_MEMORY_WISH)

    @Transactional
    suspend fun modify(userId: Long, bookMemoryWishId: Long, req: BookMemoryWishModification) {
        bookMemoryWishRepository.findByIdAndUserId(bookMemoryWishId, userId)
            ?.apply {
                memo = req.memo
                bookMemoryWishRepository.save(this)
            }
            ?: throw NotFoundException(DescriptionUtils.INVALID_BOOK_MEMORY_WISH)
    }

    @Transactional
    suspend fun delete(userId: Long, bookMemoryWishId: Long) {
        bookMemoryWishRepository.deleteByIdAndUserId(bookMemoryWishId, userId)
    }
}