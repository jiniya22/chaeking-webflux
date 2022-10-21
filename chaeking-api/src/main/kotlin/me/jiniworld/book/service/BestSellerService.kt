package me.jiniworld.book.service

import kotlinx.coroutines.flow.toList
import me.jiniworld.book.domain.repository.BestSellerRepository
import me.jiniworld.book.model.DataResponse
import org.springframework.stereotype.Service

@Service
class BestSellerService(
    private val bestSellerRepository: BestSellerRepository,
) {
    suspend fun bestSeller() =
        bestSellerRepository.findAllBookBy()
            .toList()
            .let { DataResponse(data = it) }
}