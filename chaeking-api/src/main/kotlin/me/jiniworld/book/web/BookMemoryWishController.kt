package me.jiniworld.book.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import me.jiniworld.book.config.AuthUser
import me.jiniworld.book.model.BaseResponse
import me.jiniworld.book.model.BookMemoryWishCreation
import me.jiniworld.book.model.BookMemoryWishModification
import me.jiniworld.book.service.BookMemoryWishService
import me.jiniworld.book.util.DescriptionUtils
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
        return BaseResponse.SUCCESS
    }

    @Operation(summary = "읽고 싶은 책 상세조회")
    @GetMapping("/{book_memory_wish_id}")
    suspend fun selectOne(
        authUser: AuthUser,
        @Parameter(description = DescriptionUtils.ID_BOOK_MEMORY_WISH)
        @PathVariable(name = "book_memory_wish_id") bookMemoryWishId: Long,
    ) = bookMemoryWishService.selectOne(authUser.userId, bookMemoryWishId)

    @Operation(summary = "읽고 싶은 책 수정")
    @PutMapping("/{book_memory_wish_id}")
    suspend fun modify(
        authUser: AuthUser,
        @Parameter(description = DescriptionUtils.ID_BOOK_MEMORY_WISH)
        @PathVariable(name = "book_memory_wish_id") bookMemoryWishId: Long,
        req: BookMemoryWishModification,
    ): BaseResponse {
        bookMemoryWishService.modify(authUser.userId, bookMemoryWishId, req)
        return BaseResponse.SUCCESS
    }

    @Operation(summary = "읽고 싶은 책 삭제")
    @DeleteMapping("/{book_memory_wish_id}")
    suspend fun delete(
        authUser: AuthUser,
        @Parameter(description = DescriptionUtils.ID_BOOK_MEMORY_WISH)
        @PathVariable(name = "book_memory_wish_id") bookMemoryWishId: Long,
    ): BaseResponse {
        bookMemoryWishService.delete(authUser.userId, bookMemoryWishId)
        return BaseResponse.SUCCESS
    }

}