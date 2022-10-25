package me.jiniworld.book.client

import kotlinx.coroutines.reactor.awaitSingle
import me.jiniworld.book.model.client.KakaoBookRes
import me.jiniworld.book.model.client.KakaoBookSearch
import me.jiniworld.book.model.property.BookSearchProperties
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.util.DefaultUriBuilderFactory

@Component
class KakaoWebClient(
    private val bookSearchProperties: BookSearchProperties,
) {

    suspend fun searchBook(req: KakaoBookSearch): List<KakaoBookRes.Document> =
        WebClient.create().get()
            .uri(DefaultUriBuilderFactory("https://dapi.kakao.com")
                .uriString("/v3/search/book")
                .queryParam("query", req.query)
                .queryParam("target", req.target)
                .queryParam("sort", req.sort)
                .queryParam("page", req.page)
                .queryParam("size", req.size).build())
            .header("Authorization", "KakaoAK ${bookSearchProperties.kakao.apiKey}")
            .accept(MediaType.APPLICATION_JSON)
            // onStatus(HttpStatus::is4xxClientError, response -> ...)
            .retrieve()
            .bodyToMono<KakaoBookRes>()
            .map { it.documents }
            .awaitSingle()
}