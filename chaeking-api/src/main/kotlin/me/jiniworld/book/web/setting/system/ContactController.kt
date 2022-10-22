package me.jiniworld.book.web.setting.system

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import me.jiniworld.book.config.AuthUser
import me.jiniworld.book.model.BaseResponse
import me.jiniworld.book.model.BoardCreation
import me.jiniworld.book.service.ContactService
import me.jiniworld.book.service.FaqService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*

@Tag(name = "setting-system", description = "설정-시스템(공지사항, FAQ, 이용약관, 메타정보 등)")
@RestController
@RequestMapping("/v1/contact")
class ContactController(
    private val contactService: ContactService,
) {

    @Operation(summary = "문의 목록 조회")
    @GetMapping("")
    suspend fun selectAll(
        authUser: AuthUser,
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "10") size: Int,
    ) = contactService.selectAll(authUser.userId, PageRequest.of(page, size, Sort.by(Sort.Order.desc("id"))))

    @Operation(summary = "문의 등록 (= 문의하기)")
    @PostMapping("")
    suspend fun insert(
        authUser: AuthUser,
        @RequestBody req: BoardCreation
    ): BaseResponse {
        contactService.insert(authUser.userId, req)
        return BaseResponse.SUCCESS
    }

}