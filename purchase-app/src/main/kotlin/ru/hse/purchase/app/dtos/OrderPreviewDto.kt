package ru.hse.purchase.app.dtos

import java.util.*

data class OrderPreviewDto(
    val id: UUID,
    val email: String,
)