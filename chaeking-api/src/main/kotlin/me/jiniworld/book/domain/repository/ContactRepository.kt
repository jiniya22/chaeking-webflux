package me.jiniworld.book.domain.repository

import kotlinx.coroutines.flow.Flow
import me.jiniworld.book.domain.entity.Contact
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ContactRepository: CoroutineCrudRepository<Contact, Long> {
    fun findAllByUserId(userId: Long, pageable: Pageable): Flow<Contact>
    suspend fun findByIdAndUserId(id: Long, userId: Long): Contact?
}