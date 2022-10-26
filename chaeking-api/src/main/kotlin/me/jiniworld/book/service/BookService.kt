package me.jiniworld.book.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import me.jiniworld.book.client.KakaoWebClient
import me.jiniworld.book.config.exception.NotFoundException
import me.jiniworld.book.domain.entity.Author
import me.jiniworld.book.domain.entity.BookAndAuthor
import me.jiniworld.book.domain.entity.Publisher
import me.jiniworld.book.domain.repository.*
import me.jiniworld.book.model.BookDetail
import me.jiniworld.book.model.BookMemoryWishContent
import me.jiniworld.book.model.BookSimple
import me.jiniworld.book.model.DataResponse
import me.jiniworld.book.model.client.KakaoBookSearch
import me.jiniworld.book.model.client.toBook
import me.jiniworld.book.util.BasicUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class BookService(
    private val authorRepository: AuthorRepository,
    private val bookRepository: BookRepository,
    private val bookAndAuthorRepository: BookAndAuthorRepository,
    private val bookMemoryWishRepository: BookMemoryWishRepository,
    private val kakaoWebClient: KakaoWebClient,
    private val publisherRepository: PublisherRepository,
) {

    suspend fun select(id: Long, userId: Long): DataResponse<BookDetail> =
        bookRepository.findBookDetailById(id)
            ?.run {
                bookMemoryWish = bookMemoryWishRepository.findByBookIdAndUserId(id, userId)?.let { BookMemoryWishContent(it) }
                authors = findAllAuthorNameById(id).toList()
                DataResponse(data = this)
            }
            ?: throw NotFoundException("조회되는 책이 없습니다.")

    suspend fun selectAll(ids: List<Long>): DataResponse<List<BookSimple>> =
        bookRepository.findBookSimpleByIdIn(ids)
            .map {
                it.authors = findAllAuthorNameById(it.id).toList().joinToString(",")
                it
            }
            .flowOn(Dispatchers.IO)
            .toList()
            .let { DataResponse(data = it) }

    fun findAllAuthorNameById(id: Long) =
        bookAndAuthorRepository.findAllAuthorNameByBookId(id)

    @Transactional
    suspend fun searchKakaoBook(kakaoBookSearch: KakaoBookSearch): List<Long> {
        val bookIds = mutableListOf<Long>()
        val authorMap = mutableMapOf<String, Long>()
        val publisherMap = mutableMapOf<String, Long>()
        kakaoWebClient.searchBook(kakaoBookSearch)
            .forEach { kakaoBook ->
                val book = selectBookByIsbn(kakaoBook.isbn)
                if(book != null) {
                    bookIds.add(book.id)
                } else {
                    kakaoBook.toBook().run {
                        publisher?.let { publisherName ->
                            if (!publisherMap.containsKey(publisherName)) {
                                val publisher = getPublisherByName(publisherName)
                                publisherMap[publisher.name] = publisher.id
                            }
                            publisherId = publisherMap[publisherName]
                        }
                        bookRepository.save(this)
                    }.apply {
                        authors?.forEach { authorName ->
                            if(!authorMap.containsKey(authorName)) {
                                authorMap[authorName] = getAuthorByName(authorName).id
                            }
                            bookAndAuthorRepository.save(BookAndAuthor(bookId = id, authorId = authorMap[authorName]!!))
                        }
                        bookIds.add(id)
                    }
                }
            }
        return bookIds
    }

    @Transactional
    suspend fun getPublisherByName(name: String): Publisher {
        val publisher = publisherRepository.findByName(name)
            ?: Publisher(name = name, simpleName = BasicUtils.getSimpleName(name))
        return publisherRepository.save(publisher)
    }

    @Transactional
    suspend fun getAuthorByName(name: String): Author {
        val author = authorRepository.findByName(name)
            ?: Author(name = name, simpleName = BasicUtils.getSimpleName(name))
        return authorRepository.save(author)
    }

    // TODO 추후 구조 개선 검토 필요
    suspend fun selectBookByIsbn(isbn: String): BookDetail? =
        BasicUtils.getIsbn13ByIsbn(isbn)
            ?.let{ bookRepository.findBookDetailByIsbn13(it) }


}