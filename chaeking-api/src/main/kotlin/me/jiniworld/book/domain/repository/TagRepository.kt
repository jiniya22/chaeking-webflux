package me.jiniworld.book.domain.repository

import me.jiniworld.book.domain.entity.Tag
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface TagRepository : ReactiveCrudRepository<Tag, Long>