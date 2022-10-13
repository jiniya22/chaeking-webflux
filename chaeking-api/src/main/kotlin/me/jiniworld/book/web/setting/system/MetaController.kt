package me.jiniworld.book.web.setting.system

import me.jiniworld.book.domain.entity.MetaType
import me.jiniworld.book.service.MetaService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/v1/meta")
@RestController
class MetaController(
    private val metaService: MetaService,
) {

    @GetMapping("")
    fun meta(
        @RequestParam(value = "메타 타입", required = false, defaultValue = "AOS_APP_VERSION") type: MetaType
    ) = metaService.meta(type)
}