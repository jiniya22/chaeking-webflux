package me.jiniworld.book.domain.repository

import me.jiniworld.book.domain.entity.Publisher
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface PublisherRepository: CoroutineCrudRepository<Publisher, Long> {
    suspend fun findByName(publisherName: String): Publisher?
}