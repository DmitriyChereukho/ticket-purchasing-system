package ru.hse.ticket.purchasing.system.dtos

data class AuthenticationRequest(
    val email: String,
    val password: String
)