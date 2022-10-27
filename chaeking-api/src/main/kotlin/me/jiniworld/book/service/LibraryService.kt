package me.jiniworld.book.service

import me.jiniworld.book.client.Data4LibraryWebClient
import me.jiniworld.book.model.data4library.Data4LibraryLibrary
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class LibraryService(
    private val data4LibraryWebClient: Data4LibraryWebClient,
) {

    suspend fun selectLibraries(region: String): Data4LibraryLibrary.Response {
        val res = data4LibraryWebClient.searchBook(region)
        return res
    }
}