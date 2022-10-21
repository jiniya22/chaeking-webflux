package me.jiniworld.book.service

import kotlinx.coroutines.flow.toList
import me.jiniworld.book.domain.repository.BookMemoryCompleteRepository
import me.jiniworld.book.model.BookMemoryCompleteSimple
import me.jiniworld.book.model.DataResponse
import me.jiniworld.book.util.DateTimeUtils
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class BookshelfService(
    private val bookMemoryCompleteRepository: BookMemoryCompleteRepository,
) {

    suspend fun bookshelf(userId: Long, month: String, pageable: Pageable): DataResponse<List<BookMemoryCompleteSimple>> {
        val date = DateTimeUtils.getFirstDate(month)
        val time1 = DateTimeUtils.getFirstDateTime(date)
        val time2 = DateTimeUtils.getLastDateTime(date)

        return bookMemoryCompleteRepository.findAllBookMemoryCompleteSimpleByUserIdAndCreatedAtBetween(userId, time1, time2, pageable.offset, pageable.pageSize)
            .toList()
            .let { DataResponse(data = it) }
    }
}