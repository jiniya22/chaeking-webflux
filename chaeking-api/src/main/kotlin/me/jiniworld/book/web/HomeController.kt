package me.jiniworld.book.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import me.jiniworld.book.config.AuthUser
import me.jiniworld.book.service.BookshelfService
import me.jiniworld.book.util.AnalysisType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "home", description = "홈")
@RestController
@RequestMapping("/v1/home")
class HomeController(
    private val bookshelfService: BookshelfService,
) {
    @GetMapping("")
    @Operation(summary = "사용자별 홈 화면 조회",
        description = """
            - **Authorization 헤더 필수**
            - type: daily, weekly, monthly 중 하나. 기본값은 daily
        """)
    suspend fun home(
        authUser: AuthUser,
        @Parameter(description = "조회 기준") @RequestParam(required = false, defaultValue = "daily") type: AnalysisType,
    ) = bookshelfService.home(authUser.userId, type)
}