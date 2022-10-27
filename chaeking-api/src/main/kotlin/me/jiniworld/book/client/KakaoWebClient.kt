package me.jiniworld.book.client

import kotlinx.coroutines.reactor.awaitSingle
import me.jiniworld.book.model.client.KakaoBookRes
import me.jiniworld.book.model.client.KakaoBookSearch
import me.jiniworld.book.model.property.BookSearchProperties
import me.jiniworld.book.util.BasicUtils
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Component
class KakaoWebClient(
    private val bookSearchProperties: BookSearchProperties,
) {
    companion object {
        private val webClient = WebClient.create("https://dapi.kakao.com")
    }

    suspend fun searchBook(req: KakaoBookSearch): List<KakaoBookRes.Document> =
        webClient.get()
            .uri{
                it.pathSegment("/v3/search/book")
                    .queryParams(BasicUtils.convertObjectToMultiValueMap(req))
                    .build()
            }
            .header("Authorization", "KakaoAK ${bookSearchProperties.kakao.apiKey}")
            .accept(MediaType.APPLICATION_JSON)
            // onStatus(HttpStatus::is4xxClientError, response -> ...)
            .retrieve()
            .bodyToMono<KakaoBookRes>()
            .map { it.documents }
            .awaitSingle()
}