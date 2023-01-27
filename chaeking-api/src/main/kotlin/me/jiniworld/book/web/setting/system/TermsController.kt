package me.jiniworld.book.web.setting.system

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import me.jiniworld.book.model.DataResponse
import me.jiniworld.book.service.TermsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "setting-system", description = "설정-시스템(공지사항, FAQ, 이용약관, 메타정보 등)")
@RequestMapping("/v1/terms")
@RestController
class TermsController(
    private val termsService: TermsService,
) {

    @Operation(summary = "이용 약관 목록 조회")
    @GetMapping("")
    suspend fun terms() =
        termsService.terms()
            .run { DataResponse(data = this) }
}