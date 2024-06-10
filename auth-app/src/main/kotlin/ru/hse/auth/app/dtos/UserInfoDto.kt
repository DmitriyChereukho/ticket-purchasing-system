package ru.hse.auth.app.dtos

import java.time.LocalDateTime
import java.util.*

data class UserInfoDto(
    val id: UUID,
    val username: String,
    val email: String,
    val createdAt: LocalDateTime
)