package ru.hse.purchase.app.handlers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.server.ResponseStatusException
import ru.hse.purchase.app.dtos.ErrorDetailsDto

@ControllerAdvice
class OrderExceptionHandler {
    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(
        ex: ResponseStatusException,
        request: WebRequest
    ): ResponseEntity<ErrorDetailsDto> {
        val errorDetails = ErrorDetailsDto(
            ex.statusCode,
            ex.reason ?: "",
            request.getDescription(false)
        )
        return ResponseEntity(errorDetails, ex.statusCode)
    }
}