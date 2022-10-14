package me.jiniworld.book.util

import java.time.format.DateTimeFormatter

object DateTimeUtils {

    private const val DEFAULT_PATTERN_DATE = "yyyy-MM-dd"
    private const val DEFAULT_PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss"
    private const val DEFAULT_PATTERN_TIME = "HH:mm:ss"

    val FORMATTER_DATE: DateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_PATTERN_DATE)
    val FORMATTER_DATETIME: DateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_PATTERN_DATETIME)
    val FORMATTER_TIME: DateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_PATTERN_TIME)
}