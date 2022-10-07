package me.jiniworld.book.domain.repository

import me.jiniworld.book.domain.entity.Faq
import org.springframework.data.jpa.repository.JpaRepository

interface FaqRepository : JpaRepository<Faq, Long>