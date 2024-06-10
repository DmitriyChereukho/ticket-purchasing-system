package ru.hse.auth.app.dtos

data class SignupUserDto(
    val username: String,
    val email: String,
    val password: String
)