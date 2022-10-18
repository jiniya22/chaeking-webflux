package me.jiniworld.book.service

import me.jiniworld.book.domain.entity.Book
import me.jiniworld.book.domain.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository,
) {
    suspend fun findById(bookId: Long, userId: Long): Book? =
        bookRepository.findById(bookId)
}