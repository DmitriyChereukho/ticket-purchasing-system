package ru.hse.ticket.purchasing.system.data.entities

import jakarta.persistence.*
import lombok.Builder
import lombok.Data


@Entity
@Table(name = "users")
@Builder
@Data
data class User(
    @Column(name = "username")
    val username: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    val password: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
)