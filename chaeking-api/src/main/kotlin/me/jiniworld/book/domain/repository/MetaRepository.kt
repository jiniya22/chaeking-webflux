package me.jiniworld.book.domain.repository

import me.jiniworld.book.domain.entity.Meta
import me.jiniworld.book.domain.entity.MetaType
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface MetaRepository : CoroutineCrudRepository<Meta, Long> {
    suspend fun findByType(type: MetaType): Meta?
}