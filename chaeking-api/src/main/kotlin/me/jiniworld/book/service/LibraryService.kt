package me.jiniworld.book.service

import me.jiniworld.book.client.Data4LibraryWebClient
import me.jiniworld.book.model.data4library.Data4LibraryBookExistReq
import me.jiniworld.book.util.BasicUtils
import me.jiniworld.book.util.DateTimeUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Transactional(readOnly = true)
@Service
class LibraryService(
    private val data4LibraryWebClient: Data4LibraryWebClient,
) {

    suspend fun selectLibraries(region: String) =
        data4LibraryWebClient.searchLibrary(
            mapOf(
                "region" to region,
                "pageNo" to "1",
                "pageSize" to "400"
            ))

    suspend fun hotTrend() =
        data4LibraryWebClient.hotTrend(
            mapOf(
                "searchDt" to DateTimeUtils.toString(LocalDate.now().minusDays(1)),
            ))

    suspend fun bookExist(req: Data4LibraryBookExistReq) =
        data4LibraryWebClient.bookExist(req)

}