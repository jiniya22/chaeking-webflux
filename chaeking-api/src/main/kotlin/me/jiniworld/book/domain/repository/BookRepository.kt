package me.jiniworld.book.domain.repository

import me.jiniworld.book.domain.entity.Book
import me.jiniworld.book.domain.repository.query.BookQueryRepository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface BookRepository: CoroutineCrudRepository<Book, Long>, BookQueryRepository