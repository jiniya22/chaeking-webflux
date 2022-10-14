package me.jiniworld.book.web

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import me.jiniworld.book.service.TagService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["tag"], description = "태그")
@RestController
@RequestMapping("/v1/tags")
class TagController(
    private val tagService: TagService,
) {

    @ApiOperation(value = "태그 목록 조회")
    @GetMapping("")
    suspend fun tags() =
        tagService.tags()
}