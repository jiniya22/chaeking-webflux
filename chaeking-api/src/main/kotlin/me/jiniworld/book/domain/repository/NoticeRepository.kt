package me.jiniworld.book.domain.repository

import kotlinx.coroutines.flow.Flow
import me.jiniworld.book.domain.entity.Notice
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface NoticeRepository : CoroutineCrudRepository<Notice, Long> {
    fun findAllBy(pageable: Pageable): Flow<Notice>
}