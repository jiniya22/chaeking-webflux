package me.jiniworld.book.service

import me.jiniworld.book.domain.entity.Book
import me.jiniworld.book.domain.repository.BookAndAuthorRepository
import me.jiniworld.book.domain.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class BookService(
    private val bookRepository: BookRepository,
    private val bookAndAuthorRepository: BookAndAuthorRepository,
) {
    suspend fun findById(id: Long, userId: Long): Book? =
        bookRepository.findWithPublisherNameById(id)

    fun findAllAuthorNameById(id: Long) =
        bookAndAuthorRepository.findAllAuthorNameByBookId(id)
}