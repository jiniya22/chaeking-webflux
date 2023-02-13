package me.jiniworld.book.model

import org.springframework.boot.context.properties.bind.DefaultValue
import javax.validation.constraints.Min

data class PagingRequest(
    @DefaultValue("0") @Min(0) val page: Int,
    @DefaultValue("10") @Min(1) val size: Int,
)