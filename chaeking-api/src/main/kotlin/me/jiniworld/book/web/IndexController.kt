package me.jiniworld.book.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController {

    @GetMapping("")
    fun index() = "cheaking project!!"
}