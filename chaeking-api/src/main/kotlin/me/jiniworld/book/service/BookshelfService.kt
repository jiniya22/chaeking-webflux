package me.jiniworld.book.service

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import me.jiniworld.book.config.exception.NotFoundException
import me.jiniworld.book.domain.repository.BestSellerRepository
import me.jiniworld.book.domain.repository.BookMemoryCompleteRepository
import me.jiniworld.book.domain.repository.UserRepository
import me.jiniworld.book.model.BookMemoryCompleteSimple
import me.jiniworld.book.model.DataResponse
import me.jiniworld.book.model.Home
import me.jiniworld.book.util.DateTimeUtils
import me.jiniworld.book.util.DescriptionUtils
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class BookshelfService(
    private val bestSellerRepository: BestSellerRepository,
    private val bookMemoryCompleteRepository: BookMemoryCompleteRepository,
    private val userRepository: UserRepository,
    private val bookService: BookService, // TODO 코드 개선 필요
) {

    suspend fun bookshelf(userId: Long, month: String, pageable: Pageable): DataResponse<List<BookMemoryCompleteSimple>> {
        val date = DateTimeUtils.getFirstDate(month)
        val time1 = DateTimeUtils.getFirstDateTime(date)
        val time2 = DateTimeUtils.getLastDateTime(date)

        return bookMemoryCompleteRepository.findAllBookMemoryCompleteSimpleByUserIdAndCreatedAtBetween(userId, time1, time2, pageable.offset, pageable.pageSize)
            .toList()
            .let { DataResponse(data = it) }
    }

    suspend fun bookAnalysis(userId: Long, type: AnalysisType): DataResponse<Home> {
        val nickname = userRepository.findById(userId)?.nickname
            ?: throw NotFoundException(DescriptionUtils.INVALID_USER_ID)
        val bestSeller = bestSellerRepository.findTop3BookBy()
            .map {
                it.authorNames = bookService.findAllAuthorNameById(it.id).toList().joinToString(",")
                it
            }.toList()

        return DataResponse(data = Home(nickname = nickname, bestSeller = bestSeller))
    }
}

enum class AnalysisType {
    daily, weekly, month
}