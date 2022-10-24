package me.jiniworld.book.domain.repository

import kotlinx.coroutines.flow.Flow
import me.jiniworld.book.domain.entity.BookMemoryComplete
import me.jiniworld.book.domain.repository.query.BookMemoryCompleteQueryRepository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.time.LocalDateTime

interface BookMemoryCompleteRepository: CoroutineCrudRepository<BookMemoryComplete, Long>, BookMemoryCompleteQueryRepository {
    suspend fun findByBookIdAndUserId(bookId: Long, userId: Long): BookMemoryComplete?
    suspend fun findByIdAndUserId(id: Long, userId: Long): BookMemoryComplete?
    suspend fun deleteByIdAndUserId(id: Long, userId: Long)
    fun findAllBookMemoryCompleteSimpleByUserIdAndCreatedAtBetween(userId: Long, time1: LocalDateTime, time2: LocalDateTime): Flow<BookMemoryComplete>
    suspend fun existsByUserId(userId: Long): Boolean
}