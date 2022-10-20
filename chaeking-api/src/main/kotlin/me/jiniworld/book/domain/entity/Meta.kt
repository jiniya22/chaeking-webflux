package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("meta")
class Meta(

    @Id
    val id: Long? = null,

    val type: MetaType,

    var content: String,

    var active: Boolean,
)

enum class MetaType {
    AOS_APP_VERSION, API_VERSION;
}
