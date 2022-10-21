package me.jiniworld.book.domain.repository.query

import me.jiniworld.book.domain.entity.Book
import org.springframework.data.r2dbc.repository.Query

interface BookQueryRepository {

    @Query("""SELECT b.*, p.name publisher
        FROM book b LEFT JOIN publisher p on b.publisher_id = p.id
        WHERE b.id = :id
    """)
    suspend fun findWithPublisherNameById(id: Long): Book?
}
