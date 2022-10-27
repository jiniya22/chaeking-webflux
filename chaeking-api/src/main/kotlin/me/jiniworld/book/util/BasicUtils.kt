package me.jiniworld.book.util

import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import java.lang.reflect.Method

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

    fun <T : Any> convertObjectToMultiValueMap(t: T): MultiValueMap<String, String?> {
        val multiValueMap = LinkedMultiValueMap<String, String?>()
        t.javaClass.declaredFields.forEach { field ->
            val fieldName = field.name
            val methodName = String.format("get%s%s", fieldName.substring(0, 1).uppercase(), fieldName.substring(1))
            val method: Method = t.javaClass.getDeclaredMethod(methodName)
            multiValueMap.add(fieldName, method.invoke(t)?.toString())
        }
        return multiValueMap
    }
}
