package me.jiniworld.book.domain.repository

import me.jiniworld.book.domain.entity.Author
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface AuthorRepository: CoroutineCrudRepository<Author, Long> {
    suspend fun findByName(name: String): Author?
}