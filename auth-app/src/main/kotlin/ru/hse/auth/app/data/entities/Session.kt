package ru.hse.auth.app.data.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.Builder
import lombok.Data
import java.time.LocalDateTime
import java.util.*

@Data
@Entity
@Builder
@Table(name = "sessions")
data class Session(
    @Column(name = "token")
    val token: String,

    @Column(name = "expires")
    val expirationDate: LocalDateTime,

    @Id
    @Column(name = "id")
    val id: UUID
)
