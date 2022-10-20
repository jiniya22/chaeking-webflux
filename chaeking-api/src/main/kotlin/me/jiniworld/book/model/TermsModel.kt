package me.jiniworld.book.model

import java.time.LocalDate

data class TermsModel(
    val termsId: Long,
    val title: String,
    val url: String,
    val effectiveOn: LocalDate,
)