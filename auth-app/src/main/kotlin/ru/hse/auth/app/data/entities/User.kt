package ru.hse.auth.app.data.entities

import jakarta.persistence.*
import lombok.Builder
import lombok.Data
import java.time.LocalDateTime
import java.util.*


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

    @Column(name = "created")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val sessions: List<Session>,

    @Id
    @Column(name = "id")
    val id: UUID,
)