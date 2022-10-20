package me.jiniworld.book.domain.repository

import me.jiniworld.book.domain.entity.BookMemoryComplete
import me.jiniworld.book.domain.repository.query.BookMemoryCompleteQueryRepository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface BookMemoryCompleteRepository: CoroutineCrudRepository<BookMemoryComplete, Long>, BookMemoryCompleteQueryRepository {
    suspend fun findByBookIdAndUserId(bookId: Long, userId: Long): BookMemoryComplete?
    suspend fun findByIdAndUserId(id: Long, userId: Long): BookMemoryComplete?
    suspend fun deleteByIdAndUserId(id: Long, userId: Long)
}