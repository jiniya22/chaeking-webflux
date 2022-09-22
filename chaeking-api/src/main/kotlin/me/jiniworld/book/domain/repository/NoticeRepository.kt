package me.jiniworld.book.domain.repository

import me.jiniworld.book.domain.entity.Notice
import org.springframework.data.jpa.repository.JpaRepository

interface NoticeRepository : JpaRepository<Notice, Long>