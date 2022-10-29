package me.jiniworld.book.domain.repository.query

import me.jiniworld.book.domain.entity.Library
import org.springframework.data.r2dbc.repository.Query

interface LibraryQueryRepository {
    @Query("""SELECT l.*
        FROM library l INNER JOIN user u ON l.id = u.library_id
        WHERE u.id = :userId
        LIMIT 1""")
    suspend fun findByUserId(userId: Long): Library?
}
