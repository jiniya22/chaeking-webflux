package me.jiniworld.book.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import me.jiniworld.book.service.TagService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "tag", description = "태그")
@RestController
@RequestMapping("/v1/tags")
class TagController(
    private val tagService: TagService,
) {

    @Operation(summary = "태그 목록 조회")
    @GetMapping("")
    suspend fun tags() =
        tagService.tags()
}