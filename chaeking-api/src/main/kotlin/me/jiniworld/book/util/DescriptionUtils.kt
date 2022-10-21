package me.jiniworld.book.util

object DescriptionUtils {
    const val ID_USER = "access_token (= JWT token)"
    const val ID_BOOK_MEMORY_COMPLETE = "이미 읽은 책 기록 id"
    const val ID_BOOK_MEMORY_WISH = "읽고 싶은 책 기록 id"

    const val SECRET_KEY = "uuid 또는 난수 (32자 이상)"
    const val PASSWORD = "secret_key 로 AES 암호화한 비밀번호"
    const val MONTH = "조회할 월(yyyyMM)"

    const val INVALID_BOOK_ID = "조회되는 book 정보가 없습니다(book_id Error)"
    const val INVALID_USER_ID = "조회되는 user 정보가 없습니다."
    const val INVALID_BOOK_MEMORY_WISH = "조회되는 book_memory_wish 정보가 없습니다"
    const val INVALID_BOOK_MEMORY_COMPLETE = "조회되는 book_memory_complete 정보가 없습니다"

}