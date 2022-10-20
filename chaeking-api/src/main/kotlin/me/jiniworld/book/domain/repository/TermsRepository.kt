package me.jiniworld.book.domain.repository

import me.jiniworld.book.domain.entity.Terms
import me.jiniworld.book.domain.repository.query.TermsQueryRepository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface TermsRepository : CoroutineCrudRepository<Terms, Long>, TermsQueryRepository