package me.jiniworld.book.client

import kotlinx.coroutines.reactor.awaitSingle
import me.jiniworld.book.model.data4library.Data4LibraryBookExist
import me.jiniworld.book.model.data4library.Data4LibraryBookExistReq
import me.jiniworld.book.model.data4library.Data4LibraryHotTrend
import me.jiniworld.book.model.data4library.Data4LibraryLibrary
import me.jiniworld.book.model.property.ChaekingProperties
import me.jiniworld.book.util.BasicUtils
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Component
class Data4LibraryWebClient(
    private val chaekingProperties: ChaekingProperties,
) {
    companion object {
        private val webClient = WebClient.create("https://data4library.kr/api")
    }
    private val baseQueryParams = mapOf(
        "authKey" to chaekingProperties.data4library.authKey,
        "format" to "json"
    )

    suspend fun searchLibrary(req: Map<String, String>): Data4LibraryLibrary.Response =
        webClient.get()
            .uri{
                it.pathSegment("/libSrch")
                    .queryParams(LinkedMultiValueMap<String, String>().apply {
                        setAll(baseQueryParams)
                        setAll(req)
                    })
                    .build()
            }
            .retrieve()
            .bodyToMono<Data4LibraryLibrary>()
            .map { it.response }
            .awaitSingle()

    suspend fun hotTrend(req: Map<String, String>): Data4LibraryHotTrend.Response =
        webClient.get()
            .uri{
                it.pathSegment("/hotTrend")
                    .queryParams(LinkedMultiValueMap<String, String>().apply {
                        setAll(baseQueryParams)
                        setAll(req)
                    })
                    .build()
            }
            .retrieve()
            .bodyToMono<Data4LibraryHotTrend>()
            .map { it.response }
            .awaitSingle()

    suspend fun bookExist(req: Data4LibraryBookExistReq): Data4LibraryBookExist.Response.Result =
        webClient.get()
            .uri{
                it.pathSegment("/bookExist")
                    .queryParams(LinkedMultiValueMap<String, String>().apply {
                        setAll(baseQueryParams)
                        addAll(BasicUtils.convertObjectToMultiValueMap(req))
                    })
                    .build()
            }
            .retrieve()
            .bodyToMono<Data4LibraryBookExist>()
            .map { it.response.result }
            .awaitSingle()
}