package me.jiniworld.book.util

import java.time.format.DateTimeFormatter

object DateTimeUtils {

    val DEFAULT_PATTERN_DATE = "yyyy-MM-dd"
    val DEFAULT_PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss"
    val DEFAULT_PATTERN_TIME = "HH:mm:ss"

    val FORMATTER_DATE = DateTimeFormatter.ofPattern(DEFAULT_PATTERN_DATE)
    val FORMATTER_DATETIME = DateTimeFormatter.ofPattern(DEFAULT_PATTERN_DATETIME)
    val FORMATTER_TIME = DateTimeFormatter.ofPattern(DEFAULT_PATTERN_TIME)
}