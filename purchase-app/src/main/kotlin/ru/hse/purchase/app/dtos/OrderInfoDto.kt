package ru.hse.purchase.app.dtos

import ru.hse.purchase.app.enums.OrderStatus

data class OrderInfoDto(
    val id: java.util.UUID,
    val fromStation: Int,
    val toStation: Int,
    val email: String,
    val status: OrderStatus,
    val createdAt: java.time.LocalDateTime,
)