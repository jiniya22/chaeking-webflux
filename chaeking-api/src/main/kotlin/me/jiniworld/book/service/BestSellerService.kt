package me.jiniworld.book.service

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import me.jiniworld.book.domain.repository.BestSellerRepository
import me.jiniworld.book.model.DataResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class BestSellerService(
    private val bookService: BookService,
    private val bestSellerRepository: BestSellerRepository,
) {
    suspend fun bestSeller(rowCount: Int) =
        DataResponse(data = findTopNBookBy(rowCount))

    suspend fun findTopNBookBy(rowCount: Int) =
        bestSellerRepository.findTopNBookBy(rowCount)
            .map {
                it.authorNames = bookService.findAllAuthorNameById(it.id).toList().joinToString(",")
                it
            }.toList()

}