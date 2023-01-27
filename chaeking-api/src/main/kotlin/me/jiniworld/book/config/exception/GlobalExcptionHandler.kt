package me.jiniworld.book.config.exception

import me.jiniworld.book.model.BaseResponse
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExcptionHandler {
    private val logging = KotlinLogging.logger {}

//    @ExceptionHandler(ServerException::class)
//    fun handleServerException(ex: ServerException): ResponseEntity<BaseResponse> {
//        logging.error { ex.message }
//        return ResponseEntity.status(ex.code)
//            .body(BaseResponse(result = "fail", reason = ex.message))
//    }
//
//    @ExceptionHandler(Exception::class)
//    fun handleException(ex: Exception): ResponseEntity<BaseResponse> {
//        logging.error { ex.message }
//        return ResponseEntity.internalServerError()
//            .body(BaseResponse(result = "fail", reason = "Internal Server Error"))
//    }

}