package me.jiniworld.book.domain.repository.query

import kotlinx.coroutines.flow.Flow
import me.jiniworld.book.model.BookMemoryCompleteDetail
import me.jiniworld.book.model.BookMemoryCompleteSimple
import org.springframework.data.r2dbc.repository.Query
import reactor.core.publisher.Mono
import java.time.LocalDateTime

interface BookMemoryCompleteQueryRepository {
    @Query("""SELECT c.id, book_id, b.name as book_name, b.image_url
            FROM book_memory_complete c INNER JOIN book b ON c.book_id = b.id
            WHERE c.user_id = :userId ORDER BY c.id DESC
            LIMIT :offset, :rowCount""")
    fun findAllBookMemoryCompleteSimpleByUserId(userId: Long, offset: Long, rowCount: Int): Flow<BookMemoryCompleteSimple>

    @Query("""SELECT c.id, book_id, b.name as book_name, b.image_url
            FROM book_memory_complete c INNER JOIN book b ON c.book_id = b.id
            WHERE c.user_id = :userId AND c.created_at BETWEEN :time1 AND :time2
            ORDER BY c.id DESC LIMIT :offset, :rowCount""")
    fun findAllBookMemoryCompleteSimpleByUserIdAndCreatedAtBetween(
        userId: Long, time1: LocalDateTime, time2: LocalDateTime, offset: Long, rowCount: Int): Flow<BookMemoryCompleteSimple>

    @Query("""SELECT c.id, book_id, b.name as book_name, b.image_url, memo, rate
            FROM book_memory_complete c INNER JOIN book b ON c.book_id = b.id
            WHERE c.id = :id AND c.user_id = :userId
            LIMIT 1""")
    fun findBookMemoryCompleteDetailByIdAndUserId(id: Long, userId: Long): Mono<BookMemoryCompleteDetail>
}