package me.jiniworld.book.service

import me.jiniworld.book.domain.repository.TagRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class TagService(
    private val tagRepository: TagRepository,
) {

    fun tags() =
        tagRepository.findAll()
}