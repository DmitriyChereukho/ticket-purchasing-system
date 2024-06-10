package ru.hse.purchase.app.dtos

import org.springframework.http.HttpStatusCode


data class ErrorDetailsDto(
    val statusCode: HttpStatusCode,
    val message: String,
    val details: String
)