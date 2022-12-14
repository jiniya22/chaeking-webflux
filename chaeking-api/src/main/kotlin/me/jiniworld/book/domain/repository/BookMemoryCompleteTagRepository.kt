package me.jiniworld.book.domain.repository

import kotlinx.coroutines.flow.Flow
import me.jiniworld.book.domain.entity.BookMemoryCompleteTag
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface BookMemoryCompleteTagRepository: CoroutineCrudRepository<BookMemoryCompleteTag, Long> {
    fun findAllByBookMemoryCompleteId(id: Long): Flow<BookMemoryCompleteTag>
    suspend fun deleteAllByBookMemoryCompleteId(id: Long)
    suspend fun deleteAllByBookMemoryCompleteIdAndTagIdIn(bookMemoryCompleteId: Long, tagIds: List<Long>)
}