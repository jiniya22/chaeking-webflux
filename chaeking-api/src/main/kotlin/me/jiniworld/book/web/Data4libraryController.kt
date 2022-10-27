package me.jiniworld.book.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import me.jiniworld.book.service.LibraryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.NotBlank

@Tag(name = "data4library", description = "정보나루")
@RestController
@RequestMapping("/temp/data4library")
class Data4libraryController(
    private val libraryService: LibraryService,
) {
    @Operation(summary = "도서관 조회")
    @GetMapping("")
    suspend fun bestSellerTop10(
        @Parameter(description = "검색어") @RequestParam(defaultValue = "11") @NotBlank region: String
    ) = libraryService.selectLibraries(region)

    @Operation(summary = "대출 급상승 도서 조회",
        description = "정보나루 API를 이용하여 대출 급상승 도서를 조회합니다.")
    @GetMapping("/hotTrend")
    suspend fun hotTrend() =
        libraryService.hotTrend()
}