package me.jiniworld.book.domain.repository.query

import kotlinx.coroutines.flow.Flow
import me.jiniworld.book.model.TermsModel
import org.springframework.data.r2dbc.repository.Query

interface TermsQueryRepository {

    @Query("""SELECT l.id terms_id, title, l.url, l.effective_on
        FROM terms t INNER JOIN terms_log l ON t.terms_log_id = l.id""")
    fun findAllBy(): Flow<TermsModel>

}
