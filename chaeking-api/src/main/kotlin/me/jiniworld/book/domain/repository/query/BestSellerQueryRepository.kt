package me.jiniworld.book.domain.repository.query

import kotlinx.coroutines.flow.Flow
import me.jiniworld.book.model.BookSimple
import org.springframework.data.r2dbc.repository.Query

interface BestSellerQueryRepository {
    @Query("""SELECT b.id, b.name, b.image_url
        FROM best_seller s INNER JOIN book b ON s.book_id = b.id
    """)
    fun findAllBookBy(): Flow<BookSimple>
}
