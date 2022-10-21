package me.jiniworld.book.domain.repository

import me.jiniworld.book.domain.entity.BestSeller
import me.jiniworld.book.domain.repository.query.BestSellerQueryRepository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface BestSellerRepository: CoroutineCrudRepository<BestSeller, Long>, BestSellerQueryRepository