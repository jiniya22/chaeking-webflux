package me.jiniworld.book.web

import me.jiniworld.book.service.TagService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/tags")
class TagController(
    private val tagService: TagService,
) {

    @GetMapping
    fun tags() =
        tagService.tags()
}