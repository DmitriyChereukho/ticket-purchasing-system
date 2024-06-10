package ru.hse.purchase.app.data.entities

import jakarta.persistence.*
import lombok.Builder
import lombok.Data
import ru.hse.purchase.app.enums.OrderStatus
import java.time.LocalDateTime
import java.util.*

@Data
@Entity
@Builder
@Table(name = "orders")
data class Order(
    @Column(name = "email")
    val email: String,

    @Column(name = "from_station_id")
    val fromStationId: Int,

    @Column(name = "to_station_id")
    val toStationId: Int,

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    val status: OrderStatus,

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @Id
    @Column(name = "id")
    val id: UUID
)
