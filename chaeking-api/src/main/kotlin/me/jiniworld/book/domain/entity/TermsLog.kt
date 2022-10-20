package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("terms_log")
class TermsLog(
    @Id
    val id: Long? = null,

    val url: String,

    var effectiveOn: LocalDateTime,
)