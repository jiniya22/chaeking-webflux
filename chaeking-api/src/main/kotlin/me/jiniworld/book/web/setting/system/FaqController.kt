package me.jiniworld.book.web.setting.system

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import me.jiniworld.book.service.FaqService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*

@Api(tags = ["setting-system"], description = "설정-시스템(공지사항, FAQ, 이용약관, 메타정보 등)")
@RestController
@RequestMapping("/v1/faqs")
class FaqController(
    private val faqService: FaqService,
) {

    @ApiOperation(value = "FAQ(자주묻는 질문) 목록 조회")
    @GetMapping("")
    suspend fun faqs(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "10") size: Int,
    ) = faqService.faqs(PageRequest.of(page, size, Sort.by(Sort.Order.desc("id"))))

    @ApiOperation(value = "FAQ(자주묻는 질문) 상세조회")
    @GetMapping("/{faq_id}")
    suspend fun faq(
        @PathVariable("faq_id") faqId: Long
    ) = faqService.faq(faqId)
}