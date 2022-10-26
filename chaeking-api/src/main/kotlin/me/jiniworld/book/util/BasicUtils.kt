package me.jiniworld.book.util

object BasicUtils {

    fun getIsbn13ByIsbn(isbn: String): String? {
        var isbn13: String? = null
        isbn.trim().split(" ").forEach {
            if (it.length == 13)
                isbn13 = it
        }
        return isbn13
    }

    fun getSimpleName(name: String) =
        name.replace(Regex("[^\\da-zA-Z가-힣]"), "")
}