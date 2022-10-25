package me.jiniworld.book.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import me.jiniworld.book.config.AuthUser
import me.jiniworld.book.model.BookDetail
import me.jiniworld.book.model.DataResponse
import me.jiniworld.book.model.property.ChaekingProperties
import me.jiniworld.book.service.BookService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "book", description = "책, 베스트셀러, 신간")
@RestController
@RequestMapping("/v1/books")
class BookController(
    private val bookService: BookService,
    val chaekingProperties: ChaekingProperties,
) {

    @GetMapping("")
    fun test(): ChaekingProperties {
        print(chaekingProperties)
        return chaekingProperties
    }
    @Operation(summary = "책 상세조회", description = "Authorization 헤더 설정시, 사용자가 설정한 이미 읽은 책, 읽고 싶은 책 정보를 확인할 수 있습니다.")
    @GetMapping("/{book_id}")
    suspend fun selectBook(
        authUser: AuthUser,
        @Parameter(description = "책 id") @PathVariable(name = "book_id") bookId: Long,
    ): DataResponse<BookDetail> =
        bookService.select(bookId, authUser.userId)

}