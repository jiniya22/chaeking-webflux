package me.jiniworld.book.model

interface BookMemoryCompleteReq {
    val memo: String
    val rate: Double
    val tagIds: List<Long>
}

data class BookMemoryCompleteCreation(
    val bookId: Long,
    override val memo: String,
    override val rate: Double,
    override val tagIds: List<Long>,
): BookMemoryCompleteReq

data class BookMemoryCompleteModification(
    override val memo: String,
    override val rate: Double,
    override val tagIds: List<Long>,
): BookMemoryCompleteReq

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