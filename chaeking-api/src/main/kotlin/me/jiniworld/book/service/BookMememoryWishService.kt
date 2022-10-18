package me.jiniworld.book.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import me.jiniworld.book.domain.entity.BookMemoryWish
import me.jiniworld.book.domain.repository.BookMemoryWishRepository
import me.jiniworld.book.model.BoardSimple
import me.jiniworld.book.model.BookMemoryWishSimple
import me.jiniworld.book.model.DataResponse
import me.jiniworld.book.util.DateTimeUtils
import org.apache.logging.log4j.util.Strings
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class BookMememoryWishService(
    private val bookMemoryWishRepository: BookMemoryWishRepository,
) {
    suspend fun selectAll(userId: Long, month: String?, pageable: Pageable): DataResponse<List<BookMemoryWishSimple>> {
        if (Strings.isBlank(month)) {
            return bookMemoryWishRepository.findAllByUserId(userId, pageable.offset, pageable.pageSize)
            .toList()
            .let{ DataResponse(data = it)}
        }
        val date: LocalDate = DateTimeUtils.getFirstDate(month!!)
        val time1: LocalDateTime = DateTimeUtils.getFirstDateTime(date)
        val time2: LocalDateTime = DateTimeUtils.getLastDateTime(date)
        return bookMemoryWishRepository.findAllByUserIdAndCreatedAtBetween(userId, time1, time2, pageable.offset, pageable.pageSize)
            .toList()
            .let { DataResponse(data = it) }
    }
}