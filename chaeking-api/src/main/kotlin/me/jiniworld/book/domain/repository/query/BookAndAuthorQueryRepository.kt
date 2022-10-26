package me.jiniworld.book.domain.repository.query

import kotlinx.coroutines.flow.Flow
import me.jiniworld.book.domain.entity.Author
import org.springframework.data.r2dbc.repository.Query

interface BookAndAuthorQueryRepository {
    @Query("""SELECT a.name
        FROM book_and_author b INNER JOIN author a ON b.author_id = a.id
        WHERE b.book_id = :bookId""")
    fun findAllAuthorNameByBookId(bookId: Long): Flow<String>

    @Query("""SELECT a.*
        FROM book_and_author b INNER JOIN author a ON b.author_id = a.id
        WHERE b.book_id = :bookId""")
    fun findAllAuthorByBookId(bookId: Long): Flow<Author>

}
