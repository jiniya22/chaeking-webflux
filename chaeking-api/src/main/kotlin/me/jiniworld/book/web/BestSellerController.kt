package me.jiniworld.book.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import me.jiniworld.book.service.BestSellerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "book", description = "책, 베스트셀러, 신간")
@RestController
@RequestMapping("/v1/best-sellers")
class BestSellerController(
    private val bestSellerService: BestSellerService,
) {
    @Operation(
        summary = "베스트셀러 Top 10 조회",
        description = "베스트셀러 목록을 조회합니다.<br>" +
                "Kakao API 로 부터 검색되지 않거나 미성년자 구입 불가한 책은 제외하여 조회됩니다. (최대 10개)"
    )
    @GetMapping("")
    suspend fun bestSellerTop10() =
        bestSellerService.bestSeller()
}