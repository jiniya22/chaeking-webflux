package me.jiniworld.book.domain.repository

import me.jiniworld.book.domain.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository : JpaRepository<Tag, Long>