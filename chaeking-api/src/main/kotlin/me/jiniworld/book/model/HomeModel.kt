package me.jiniworld.book.model

import me.jiniworld.book.util.AnalysisType

data class Home(
    val nickname: String,
    val bestSeller: List<BookSimple>,
    val bookAnalysis: BookAnalysis?,
)

data class BookAnalysis(
    val type: AnalysisType,
    var totalAmount: Int = 0,
    val contents: MutableList<BookAnalysisContent> = mutableListOf()
) {
    fun addContent(name: String, amount: Int) {
        this.contents.add(BookAnalysisContent(name = name, amount = amount))
    }
}

data class BookAnalysisContent(
    val name: String,
    val amount: Int,
)