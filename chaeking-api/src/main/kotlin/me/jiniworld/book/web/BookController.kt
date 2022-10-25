package me.jiniworld.book.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import me.jiniworld.book.config.AuthUser
import me.jiniworld.book.model.BookDetail
import me.jiniworld.book.model.BookSimple
import me.jiniworld.book.model.DataResponse
import me.jiniworld.book.model.client.KakaoBookSearch
import me.jiniworld.book.model.client.KakaoBookSort
import me.jiniworld.book.model.client.KakaoBookTarget
import me.jiniworld.book.model.property.BookSearchProperties
import me.jiniworld.book.service.BookService
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@Tag(name = "book", description = "책, 베스트셀러, 신간")
@RestController
@RequestMapping("/v1/books")
class BookController(
    private val bookService: BookService,
    val chaekingProperties: BookSearchProperties,
) {

    @Operation(summary = "책 목록 조회",
        description = "카카오 API 를 이용하여 책 목록을 조회합니다.\n" +
                "<ul>\n" +
                "    <li>page: 0 ~ 99 사이의 값 (default: 0)</li>\n" +
                "    <li>size: 1 ~ 50 사이의 값 (default: 10)</li>\n" +
                "    <li>검색 필드 제한\n" +
                "        <ul>\n" +
                "            <li>title: 제목</li>\n" +
                "            <li>isbn: isbn</li>\n" +
                "            <li>publisher: 출판사</li>\n" +
                "            <li>person: 인명</li>\n" +
                "        </ul>\n" +
                "    </li>\n" +
                "    <li>정렬 옵션\n" +
                "        <ul>\n" +
                "            <li>accuracy: 정확도순</li>\n" +
                "            <li>latest: 발간일순</li>\n" +
                "        </ul>\n" +
                "    </li>\n" +
                "</ul>")
    @GetMapping("")
    suspend fun searchKakaoBook(
        @Parameter(description = "검색어") @RequestParam @NotBlank query: String,
        @Parameter(description = "검색 필드 제한") @RequestParam(required = false) target: KakaoBookTarget?,
        @Parameter(description = "정렬 옵션") @RequestParam(defaultValue = "accuracy") sort: KakaoBookSort,
        @RequestParam(value = "page", required = false, defaultValue = "0") @Min(0) @Max(100) page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "10") @Min(1) @Max(50) size: Int,
    ): DataResponse<List<BookSimple>> {

        val bookId: List<Long> = bookService.searchKakaoBook(KakaoBookSearch(query = query, target = target?.name ?: "",
            sort = sort.name, page = page + 1, size = size))
        return DataResponse(data = ArrayList()) // TODO
    }

    @Operation(summary = "책 상세조회", description = "Authorization 헤더 설정시, 사용자가 설정한 이미 읽은 책, 읽고 싶은 책 정보를 확인할 수 있습니다.")
    @GetMapping("/{book_id}")
    suspend fun selectBook(
        authUser: AuthUser,
        @Parameter(description = "책 id") @PathVariable(name = "book_id") bookId: Long,
    ): DataResponse<BookDetail> =
        bookService.select(bookId, authUser.userId)

    @GetMapping("/test")
    fun test() = chaekingProperties
}