package me.jiniworld.book.model.client

data class KakaoBookSearch(
    val query: String,
    val target: String,
    val sort: String,
    val page: Int,
    val size: Int,
)

data class KakaoBookRes(
    val documents: List<Document>,
    val meta: Meta,
) {
    data class Document(
        val title: String,
        val thumbnail: String?,
        val authors: List<String>,
        val price: Int,
        val publisher: String,
//        val datetime: String,
        val isbn: String,
        val url: String,
        val contents: String?,
    )

    data class Meta(
        val is_end: Boolean,
        val pageable_count: Int,
        val total_count: Int,
    )
}

enum class KakaoBookTarget {
    title, isbn, publisher, person;
}
enum class KakaoBookSort {
    accuracy, latest;
}