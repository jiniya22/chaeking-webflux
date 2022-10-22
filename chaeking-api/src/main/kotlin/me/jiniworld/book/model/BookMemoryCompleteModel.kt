package me.jiniworld.book.model

data class BookMemoryCompleteCreation(
    val bookId: Long,
    val memo: String,
    val rate: Double,
    val tagIds: List<Long>,
)

data class BookMemoryCompleteModification(
    val memo: String,
    val rate: Double,
    val tagIds: List<Long>,
)

data class BookMemoryCompleteSimple(
    val id: Long,
    val bookId: Long,
    val bookName: String,
    val imageUrl: String,
)

data class BookMemoryCompleteDetail(
    val id: Long,
    val bookId: Long,
    val bookName: String,
    val imageUrl: String,
    val memo: String,
    val rate: Double,
    var tagIds: List<Long>?,
)

//data class BookMemoryCompleteContent(
//    val id: Long,
//    val memo: String,
//    val rate: Double,
//    val tagIds: List<Long>,
//) {
//    companion object {
//        operator fun invoke(bookMemoryComplete: BookMemoryComplete) =
//            with(bookMemoryComplete) {
//                BookMemoryCompleteContent(
//                    id = id!!,
//                    memo = memo,
//                    rate = rate,
//                )
//            }
//    }
//}