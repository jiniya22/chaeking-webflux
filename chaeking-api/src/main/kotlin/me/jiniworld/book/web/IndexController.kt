package me.jiniworld.book.web

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class IndexController {

//    @GetMapping("")
//    fun index(): ResponseEntity<Unit> =
//        ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT)
//            .location(URI.create("/swagger-ui.html")).build()

    @GetMapping("/health-check")
    fun healthCheck() = "success"

}