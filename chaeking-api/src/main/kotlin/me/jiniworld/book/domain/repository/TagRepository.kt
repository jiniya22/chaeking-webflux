package me.jiniworld.book.domain.repository

import me.jiniworld.book.domain.entity.Tag
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface TagRepository : CoroutineCrudRepository<Tag, Long>