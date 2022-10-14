package me.jiniworld.book.model

open class BaseResponse(
    open val result: String = "fail",
    open val reason: String,
)

class DataResponse<T>(
    override val result: String = "success",
    override val reason: String = "",
    val data: T,
): BaseResponse(result, reason)