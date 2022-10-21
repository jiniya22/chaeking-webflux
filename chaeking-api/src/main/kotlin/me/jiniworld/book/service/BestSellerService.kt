package me.jiniworld.book.service

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import me.jiniworld.book.domain.repository.BestSellerRepository
import me.jiniworld.book.model.DataResponse
import org.springframework.stereotype.Service

@Service
class BestSellerService(
    private val bookService: BookService,
    private val bestSellerRepository: BestSellerRepository,
) {
    suspend fun bestSeller() =
        bestSellerRepository.findAllBookBy()
            .map {
                it.authorNames = bookService.findAllAuthorNameById(it.id).toList().joinToString(",")
                it
            }
            .toList()
            .let {
                DataResponse(data = it)
            }
}