package me.jiniworld.book.domain.repository

import me.jiniworld.book.domain.entity.BookAndAuthor
import me.jiniworld.book.domain.repository.query.BookAndAuthorQueryRepository
import me.jiniworld.book.domain.repository.query.BookQueryRepository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface BookAndAuthorRepository: CoroutineCrudRepository<BookAndAuthor, Long>, BookAndAuthorQueryRepository