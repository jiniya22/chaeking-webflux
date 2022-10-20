package me.jiniworld.book.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import me.jiniworld.book.config.AuthUser
import me.jiniworld.book.model.BaseResponse
import me.jiniworld.book.model.BookMemoryWishCreation
import me.jiniworld.book.service.BookMemoryWishService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*

@Tag(name = "book-memory-wish", description = "북 메모리(읽고 싶은책)")
@RestController
@RequestMapping("/v1/book-memories/wish")
class BookMemoryWishController(
    private val bookMemoryWishService: BookMemoryWishService,
) {
    @Operation(summary = "읽고 싶은 책 목록")
    @GetMapping("")
    suspend fun selectAll(
        authUser: AuthUser,
        @Parameter(description = "yyyyMM") @RequestParam(required = false) month: String?,
        @RequestParam(value = "page", required = false, defaultValue = "0") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "10") size: Int) =
        bookMemoryWishService.selectAll(
            authUser.userId,
            month,
            PageRequest.of(page, size, Sort.by(Sort.Order.desc("id")))
    )

    @Operation(summary = "읽고 싶은 책 등록", responses = [ApiResponse(responseCode = "201")])
    @PostMapping("")
    suspend fun insert(
        authUser: AuthUser,
        req: BookMemoryWishCreation,
    ): BaseResponse {
        bookMemoryWishService.insert(authUser.userId, req)
        return BaseResponse()
    }

}