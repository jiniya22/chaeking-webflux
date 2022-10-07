package me.jiniworld.book

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class ChaekingApiApplication

fun main(args: Array<String>) {
    Locale.setDefault(Locale.KOREA)
    runApplication<ChaekingApiApplication>(*args)
}
