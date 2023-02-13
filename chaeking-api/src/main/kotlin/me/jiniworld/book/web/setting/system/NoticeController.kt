package me.jiniworld.book.web.setting.system

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import me.jiniworld.book.model.BoardDetail
import me.jiniworld.book.model.DataResponse
import me.jiniworld.book.model.PagingRequest
import me.jiniworld.book.service.NoticeService
import org.springframework.web.bind.annotation.*

@Tag(name = "setting-system", description = "설정-시스템(공지사항, FAQ, 이용약관, 메타정보 등)")
@RestController
@RequestMapping("/v1/notices")
class NoticeController(
    private val noticeService: NoticeService,
) {

    @Operation(summary = "공지사항 목록 조회")
    @GetMapping("")
    suspend fun notices(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "10") size: Int,
    ) = noticeService.notices(PagingRequest(page, size))
        .run { DataResponse(data = this) }

    @Operation(summary = "공지사항 상세조회")
    @GetMapping("/{notice_id}")
    suspend fun notice(
        @PathVariable("notice_id") noticeId: Long
    ) = noticeService.notice(noticeId)
        .run { DataResponse(data = BoardDetail(this)) }
}