package me.jiniworld.book.domain.repository

import me.jiniworld.book.domain.entity.BookMemoryCompleteTag
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface BookMemoryCompleteTagRepository: CoroutineCrudRepository<BookMemoryCompleteTag, Long>