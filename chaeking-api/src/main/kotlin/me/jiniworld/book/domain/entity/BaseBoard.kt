package me.jiniworld.book.domain.entity

import org.springframework.data.annotation.Id

abstract class BaseBoard(
    @Id
    var id: Long = 0,   // TODO 구조 개선 확인 필요

    var title: String,

    var content: String,

): BaseEntity()