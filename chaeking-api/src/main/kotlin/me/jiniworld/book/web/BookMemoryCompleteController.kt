package me.jiniworld.book.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import me.jiniworld.book.config.AuthUser
import me.jiniworld.book.model.BaseResponse
import me.jiniworld.book.model.BookMemoryCompleteCreation
import me.jiniworld.book.model.BookMemoryCompleteModification
import me.jiniworld.book.service.BookMemoryCompleteService
import me.jiniworld.book.util.DescriptionUtils
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*

@Tag(name = "book-memory-complete", description = "북 메모리(이미 읽은책)")
@RestController
@RequestMapping("/v1/book-memories/complete")
class BookMemoryCompleteController(
    private val bookMemoryCompleteService: BookMemoryCompleteService,
) {
    @Operation(summary = "이미 읽은 책 목록")
    @GetMapping("")
    suspend fun selectAll(
        authUser: AuthUser,
        @Parameter(description = "yyyyMM") @RequestParam(required = false) month: String?,
        @RequestParam(value = "page", required = false, defaultValue = "0") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "10") size: Int) =
        bookMemoryCompleteService.selectAll(
            authUser.userId,
            month,
            PageRequest.of(page, size, Sort.by(Sort.Order.desc("id")))
    )

    @Operation(summary = "이미 읽은 책 등록", responses = [ApiResponse(responseCode = "201")])
    @PostMapping("")
    suspend fun insert(
        authUser: AuthUser,
        req: BookMemoryCompleteCreation,
    ): BaseResponse {
        bookMemoryCompleteService.insert(authUser.userId, req)
        return BaseResponse.SUCCESS
    }

    @Operation(summary = "이미 읽은 책 상세조회")
    @GetMapping("/{book_memory_complete_id}")
    suspend fun selectOne(
        authUser: AuthUser,
        @Parameter(description = DescriptionUtils.ID_BOOK_MEMORY_COMPLETE)
        @PathVariable(name = "book_memory_complete_id") bookMemoryCompleteId: Long,
    ) = bookMemoryCompleteService.selectOne(authUser.userId, bookMemoryCompleteId)

    @Operation(summary = "이미 읽은 책 수정")
    @PutMapping("/{book_memory_complete_id}")
    suspend fun modify(
        authUser: AuthUser,
        @Parameter(description = DescriptionUtils.ID_BOOK_MEMORY_COMPLETE)
        @PathVariable(name = "book_memory_complete_id") bookMemoryCompleteId: Long,
        req: BookMemoryCompleteModification,
    ): BaseResponse {
        bookMemoryCompleteService.modify(authUser.userId, bookMemoryCompleteId, req)
        return BaseResponse.SUCCESS
    }

    @Operation(summary = "이미 읽은 책 삭제")
    @DeleteMapping("/{book_memory_complete_id}")
    suspend fun delete(
        authUser: AuthUser,
        @Parameter(description = DescriptionUtils.ID_BOOK_MEMORY_COMPLETE)
        @PathVariable(name = "book_memory_complete_id") bookMemoryCompleteId: Long,
    ): BaseResponse {
        bookMemoryCompleteService.delete(authUser.userId, bookMemoryCompleteId)
        return BaseResponse.SUCCESS
    }

}