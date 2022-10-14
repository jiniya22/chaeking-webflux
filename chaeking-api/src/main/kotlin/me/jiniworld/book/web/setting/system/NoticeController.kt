package me.jiniworld.book.web.setting.system

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import me.jiniworld.book.service.NoticeService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*

@Api(tags = ["setting-system"], description = "설정-시스템(공지사항, FAQ, 이용약관, 메타정보 등)")
@RestController
@RequestMapping("/v1/notices")
class NoticeController(
    private val noticeService: NoticeService,
) {

    @ApiOperation(value = "공지사항 목록 조회")
    @GetMapping("")
    suspend fun notices(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "10") size: Int,
    ) = noticeService.notices(PageRequest.of(page, size, Sort.by(Sort.Order.desc("id"))))

    @ApiOperation(value = "공지사항 상세조회")
    @GetMapping("/{notice_id}")
    suspend fun notice(
        @PathVariable("notice_id") noticeId: Long
    ) = noticeService.notice(noticeId)

}