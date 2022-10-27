package me.jiniworld.book.domain.repository

import me.jiniworld.book.domain.entity.Library
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface LibraryRepository: CoroutineCrudRepository<Library, Long> {
    suspend fun findByCode(code: String): Library?
}