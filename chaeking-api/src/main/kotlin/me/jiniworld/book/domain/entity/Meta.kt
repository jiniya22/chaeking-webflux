package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("meta")
class Meta(

    @Id
    val id: Long? = null,

    @Column
    val type: MetaType,

    @Column
    var content: String,

    @Column
    var active: Boolean,
)

enum class MetaType {
    AOS_APP_VERSION, API_VERSION;
}
