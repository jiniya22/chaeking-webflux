package me.jiniworld.book.service

import kotlinx.coroutines.flow.toList
import me.jiniworld.book.config.exception.NotFoundException
import me.jiniworld.book.domain.repository.BookMemoryCompleteRepository
import me.jiniworld.book.domain.repository.UserRepository
import me.jiniworld.book.model.BookAnalysis
import me.jiniworld.book.model.Bookshelf
import me.jiniworld.book.model.DataResponse
import me.jiniworld.book.model.Home
import me.jiniworld.book.util.AnalysisType
import me.jiniworld.book.util.DateTimeUtils
import me.jiniworld.book.util.DescriptionUtils
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

@Transactional(readOnly = true)
@Service
class BookshelfService(
    private val bestSellerService: BestSellerService,
    private val bookMemoryCompleteRepository: BookMemoryCompleteRepository,
    private val userRepository: UserRepository,
) {

    suspend fun bookshelf(userId: Long, month: String, pageable: Pageable): DataResponse<List<Bookshelf>> {
        val date = DateTimeUtils.getFirstDate(month)
        val time1 = DateTimeUtils.getFirstDateTime(date)
        val time2 = DateTimeUtils.getLastDateTime(date)

        return bookMemoryCompleteRepository.findAllBookshelfByUserIdAndCreatedAtBetween(userId, time1, time2, pageable.offset, pageable.pageSize)
            .toList()
            .let { DataResponse(data = it) }
    }

    suspend fun home(userId: Long, type: AnalysisType): DataResponse<Home> {
        val nickname = userRepository.findById(userId)?.nickname
            ?: throw NotFoundException(DescriptionUtils.INVALID_USER_ID)
        val bestSeller = bestSellerService.findTopNBookBy(3)
        val bookAnalysis = if(bookMemoryCompleteRepository.existsByUserId(userId)) bookAnalysis(userId, type) else null
        return DataResponse(data = Home(nickname = nickname, bestSeller = bestSeller, bookAnalysis = bookAnalysis))
    }

    suspend fun bookAnalysis(userId: Long, type: AnalysisType): BookAnalysis {
        val res = BookAnalysis(type)

        val today = LocalDate.now()
        val time1 = DateTimeUtils.getFirstDateTime(today, type)
        val time2 = DateTimeUtils.getLastDateTime(today, type)
        val periodArr = createPeriodArr(time1, type)
        val cntArr = (0 until 7).map { 0 }.toMutableList()
        bookMemoryCompleteRepository.findAllBookMemoryCompleteSimpleByUserIdAndCreatedAtBetween(userId, time1, time2).collect { b ->
            if (b.createdAt?.isAfter(periodArr[6]) == true) {
                cntArr[6]++
            } else if (b.createdAt?.isAfter(periodArr[5]) == true) {
                cntArr[5]++
            } else if (b.createdAt?.isAfter(periodArr[4]) == true) {
                cntArr[4]++
            } else if (b.createdAt?.isAfter(periodArr[3]) == true) {
                cntArr[3]++
            } else if (b.createdAt?.isAfter(periodArr[2]) == true) {
                cntArr[2]++
            } else if (b.createdAt?.isAfter(periodArr[1]) == true) {
                cntArr[1]++
            } else if (b.createdAt?.isAfter(periodArr[0]) == true) {
                cntArr[0]++
            }
        }
        (0 until cntArr.size).forEach {
            val pattern = when (type) {
                AnalysisType.daily -> "E"
                AnalysisType.monthly -> "MM"
                else -> "MM.dd"
            }
            res.addContent(DateTimeFormatter.ofPattern(pattern).format(periodArr[it]), cntArr[it])
        }
        return res
    }

    fun createPeriodArr(firstDateTime: LocalDateTime, type: AnalysisType) =
        ArrayList<LocalDateTime>()
            .let {
                for (i in 0 until 7) {
                    it.add(
                        when (type) {
                            AnalysisType.weekly -> firstDateTime.plusWeeks(i.toLong())
                            AnalysisType.monthly -> firstDateTime.plusMonths(i.toLong()).with(TemporalAdjusters.firstDayOfMonth())
                            else -> firstDateTime.plusDays(i.toLong())
                        })
                }
                it
            }

}
