package me.jiniworld.book.service

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import me.jiniworld.book.domain.repository.TagRepository
import me.jiniworld.book.model.BasicModel
import me.jiniworld.book.model.response.DataResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class TagService(
    private val tagRepository: TagRepository,
) {

    suspend fun tags() =
        tagRepository.findAll()
            .map { BasicModel(it) }
            .toList()
            .let { DataResponse(data = it) }
}