package me.jiniworld.book.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import me.jiniworld.book.config.AuthUser
import me.jiniworld.book.service.BookshelfService
import me.jiniworld.book.util.DateTimeUtils
import me.jiniworld.book.util.DescriptionUtils
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Tag(name = "bookshelf", description = "책장")
@RestController
@RequestMapping("/v1/bookshelf")
class BookshelfController(
    private val bookshelfService: BookshelfService,
) {
    @GetMapping("")
    @Operation(
        summary = "사용자별 책장 조회",
        description = """
            - month: yyyyMM 형태로 입력. 입력하지 않은 경우 오늘 날짜를 이용합니다
            - page: 0 ~ 999 사이의 값. 기본값은 0
            - size: 1 ~ 999 사이의 값. 기본값은 12
            - **Authorization 헤더 필수**
        """)
    suspend fun bookshelf(
        authUser: AuthUser,
        @Parameter(description = DescriptionUtils.MONTH) @RequestParam(required = false) month: String?,
        @RequestParam(value = "page", required = false, defaultValue = "0") @Min(0) @Max(999) page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "12") @Min(1) @Min(999) size: Int,
    ) = bookshelfService.bookshelf(
        authUser.userId,
        if(month.isNullOrBlank()) LocalDate.now().format(DateTimeUtils.FORMATTER_MONTH_SIMPLE) else month,
        PageRequest.of(page, size, Sort.by(Sort.Order.desc("id"))))
}