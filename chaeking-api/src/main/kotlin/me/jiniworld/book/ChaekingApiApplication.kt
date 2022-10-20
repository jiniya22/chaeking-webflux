package me.jiniworld.book

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import java.util.*

@EnableR2dbcAuditing
@SpringBootApplication
class ChaekingApiApplication

fun main(args: Array<String>) {
    Locale.setDefault(Locale.KOREA)
    runApplication<ChaekingApiApplication>(*args)
}
