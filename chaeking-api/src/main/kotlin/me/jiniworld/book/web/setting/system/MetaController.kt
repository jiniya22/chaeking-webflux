package me.jiniworld.book.web.setting.system

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import me.jiniworld.book.domain.entity.MetaType
import me.jiniworld.book.service.MetaService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["setting-system"], description = "설정-시스템(공지사항, FAQ, 이용약관, 메타정보 등)")
@RequestMapping("/v1/meta")
@RestController
class MetaController(
    private val metaService: MetaService,
) {

    @ApiOperation(value = "메타 정보(앱 버전) 조회")
    @GetMapping("")
    fun meta(
        @RequestParam(value = "메타 타입", required = false, defaultValue = "AOS_APP_VERSION") type: MetaType
    ) = metaService.meta(type)
}