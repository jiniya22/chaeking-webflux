package me.jiniworld.book.client

import kotlinx.coroutines.reactor.awaitSingle
import me.jiniworld.book.model.data4library.Data4LibraryLibrary
import me.jiniworld.book.model.property.ChaekingProperties
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.util.DefaultUriBuilderFactory

@Component
class Data4LibraryWebClient(
    private val chaekingProperties: ChaekingProperties,
) {

    suspend fun searchBook(region: String): Data4LibraryLibrary.Response =
        WebClient.create().get()
            .uri(DefaultUriBuilderFactory("https://data4library.kr/api")
                .uriString("/libSrch")
                .queryParam("authKey", chaekingProperties.data4library.authKey)
                .queryParam("format", "json")
                .queryParam("region", region)
                .queryParam("pageNo", 1)
                .queryParam("pageSize", 300).build())
            .retrieve()
            .bodyToMono<Data4LibraryLibrary>()
            .map { it.response }
            .awaitSingle()
}