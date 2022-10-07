package me.jiniworld.book.web

import me.jiniworld.book.model.BoardSimple
import me.jiniworld.book.service.NoticeService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/notices")
class NoticeController(
    private val noticeService: NoticeService,
) {

    @GetMapping("")
    fun notices(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "10") size: Int,
    ) = noticeService.notices(PageRequest.of(page, size, Sort.by(Sort.Order.desc("id"))))

    @GetMapping("/{notice_id}")
    fun notice(
        @PathVariable("notice_id") noticeId: Long
    ) = noticeService.notice(noticeId)
}