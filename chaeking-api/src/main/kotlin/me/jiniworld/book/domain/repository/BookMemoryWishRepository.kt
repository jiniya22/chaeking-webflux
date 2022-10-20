package me.jiniworld.book.domain.repository

import me.jiniworld.book.domain.entity.BookMemoryWish
import me.jiniworld.book.domain.repository.query.BookMemoryWishQueryRepository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface BookMemoryWishRepository: CoroutineCrudRepository<BookMemoryWish, Long>, BookMemoryWishQueryRepository {
    suspend fun findByBookIdAndUserId(bookId: Long, userId: Long): BookMemoryWish?
    suspend fun findByIdAndUserId(id: Long, userId: Long): BookMemoryWish?
    suspend fun deleteByIdAndUserId(id: Long, userId: Long)
}