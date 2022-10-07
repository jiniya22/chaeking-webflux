package me.jiniworld.book.web

import me.jiniworld.book.service.FaqService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/faqs")
class FaqController(
    private val faqService: FaqService,
) {

    @GetMapping("")
    fun faqs(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "10") size: Int,
    ) = faqService.faqs(PageRequest.of(page, size, Sort.by(Sort.Order.desc("id"))))

    @GetMapping("/{faq_id}")
    fun faq(
        @PathVariable("faq_id") faqId: Long
    ) = faqService.faq(faqId)
}