package me.jiniworld.book.domain.repository

import me.jiniworld.book.domain.entity.Library
import me.jiniworld.book.domain.repository.query.LibraryQueryRepository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface LibraryRepository: CoroutineCrudRepository<Library, Long>, LibraryQueryRepository {
    suspend fun findByCode(code: String): Library?
}