package me.jiniworld.book.service

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactor.awaitSingle
import me.jiniworld.book.client.KakaoWebClient
import me.jiniworld.book.config.exception.NotFoundException
import me.jiniworld.book.domain.repository.BookAndAuthorRepository
import me.jiniworld.book.domain.repository.BookMemoryWishRepository
import me.jiniworld.book.domain.repository.BookRepository
import me.jiniworld.book.model.BookDetail
import me.jiniworld.book.model.DataResponse
import me.jiniworld.book.model.client.KakaoBookSearch
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class BookService(
    private val bookRepository: BookRepository,
    private val bookAndAuthorRepository: BookAndAuthorRepository,
    private val bookMemoryWishRepository: BookMemoryWishRepository,
    private val kakaoWebClient: KakaoWebClient,
) {

    suspend fun select(id: Long, userId: Long): DataResponse<BookDetail> =
        bookRepository.findWithPublisherNameById(id)
            ?.run {
                bookMemoryWish = bookMemoryWishRepository.findByBookIdAndUserId(id, userId)
                authors = findAllAuthorNameById(id).toList()
                DataResponse(data = BookDetail(this))
            }
            ?: throw NotFoundException("조회되는 책이 없습니다.")

    fun findAllAuthorNameById(id: Long) =
        bookAndAuthorRepository.findAllAuthorNameByBookId(id)

    suspend fun searchKakaoBook(kakaoBookSearch: KakaoBookSearch): List<Long> {
        val ttt = kakaoWebClient.searchBook(kakaoBookSearch)
        println(ttt)
        return ArrayList()  // TODO
    }
}