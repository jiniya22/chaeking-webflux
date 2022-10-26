package me.jiniworld.book.domain.repository.query

import kotlinx.coroutines.flow.Flow
import me.jiniworld.book.domain.entity.Book
import me.jiniworld.book.model.BookDetail
import me.jiniworld.book.model.BookSimple
import org.springframework.data.r2dbc.repository.Query

interface BookQueryRepository {

    @Query("""SELECT b.*, b.isbn13 isbn, p.name publisher
        FROM book b LEFT JOIN publisher p on b.publisher_id = p.id
        WHERE b.id = :id""")
    suspend fun findBookDetailById(id: Long): BookDetail?

    @Query("""SELECT b.*, b.isbn13 isbn, p.name publisher
        FROM book b LEFT JOIN publisher p on b.publisher_id = p.id
        WHERE b.isbn13 = :isbn13
        LIMIT 1""")
    suspend fun findBookDetailByIsbn13(isbn13: String): BookDetail?

    @Query("""SELECT b.id, b.name, b.image_url, p.name publisher
        FROM book b LEFT JOIN publisher p on b.publisher_id = p.id
        WHERE b.id IN (:ids)""")
    fun findBookSimpleByIdIn(ids: List<Long>): Flow<BookSimple>

}
