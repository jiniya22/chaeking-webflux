package me.jiniworld.book.domain.repository

import kotlinx.coroutines.flow.Flow
import me.jiniworld.book.domain.entity.BookMemoryWish
import me.jiniworld.book.model.BookMemoryWishSimple
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.time.LocalDateTime

interface BookMemoryWishRepository: CoroutineCrudRepository<BookMemoryWish, Long> {
    @Query("SELECT w.id, book_id, b.name as book_name " +
            "FROM book_memory_wish w INNER JOIN book b ON w.book_id = b.id " +
            "WHERE w.user_id = :userId ORDER BY w.id DESC " +
            "LIMIT :offset, :rowCount")
    fun findAllByUserId(userId: Long, offset: Long, rowCount: Int): Flow<BookMemoryWishSimple>

    @Query("SELECT w.id, book_id, b.name as book_name " +
            "FROM book_memory_wish w INNER JOIN book b ON w.book_id = b.id " +
            "WHERE w.user_id = :userId AND w.created_at BETWEEN :time1 AND :time2 " +
            "ORDER BY w.id DESC LIMIT :offset, :rowCount")
    fun findAllByUserIdAndCreatedAtBetween(userId: Long, time1: LocalDateTime, time2: LocalDateTime, offset: Long, rowCount: Int): Flow<BookMemoryWishSimple>
}
