package ru.hse.auth.app.dtos

data class AuthenticationRequest(
    val email: String,
    val password: String
)