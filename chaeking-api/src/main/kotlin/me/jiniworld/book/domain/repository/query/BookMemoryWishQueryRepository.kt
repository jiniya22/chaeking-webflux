package me.jiniworld.book.domain.repository.query

import kotlinx.coroutines.flow.Flow
import me.jiniworld.book.model.BookMemoryWishSimple
import org.springframework.data.r2dbc.repository.Query
import java.time.LocalDateTime

interface BookMemoryWishQueryRepository {
    @Query("""SELECT w.id, book_id, b.name as book_name
            FROM book_memory_wish w INNER JOIN book b ON w.book_id = b.id
            WHERE w.user_id = :userId ORDER BY w.id DESC
            LIMIT :offset, :rowCount""")
    fun findAllBookMemoryWishSimpleByUserId(userId: Long, offset: Long, rowCount: Int): Flow<BookMemoryWishSimple>

    @Query("""SELECT w.id, book_id, b.name as book_name
            FROM book_memory_wish w INNER JOIN book b ON w.book_id = b.id
            WHERE w.user_id = :userId AND w.created_at BETWEEN :time1 AND :time2
            ORDER BY w.id DESC LIMIT :offset, :rowCount""")
    fun findAllBookMemoryWishSimpleByUserIdAndCreatedAtBetween(
        userId: Long, time1: LocalDateTime, time2: LocalDateTime, offset: Long, rowCount: Int): Flow<BookMemoryWishSimple>

}