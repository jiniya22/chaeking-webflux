package me.jiniworld.book.domain.repository.query

import kotlinx.coroutines.flow.Flow
import me.jiniworld.book.model.BookSimple
import org.springframework.data.r2dbc.repository.Query

interface BestSellerQueryRepository {
    @Query("""SELECT b.id, b.name, b.image_url, p.name publisher
        FROM best_seller s INNER JOIN book b ON s.book_id = b.id
            LEFT JOIN publisher p on b.publisher_id = p.id
        LIMIT :rowCount
    """)
    fun findTopNBookBy(rowCount: Int): Flow<BookSimple>
}
