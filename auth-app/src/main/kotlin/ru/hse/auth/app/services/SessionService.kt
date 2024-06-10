package ru.hse.auth.app.services

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import ru.hse.auth.app.data.entities.Session
import java.time.ZoneId
import java.util.*

@Service
class SessionService(private val jwtService: JwtService, private val userService: UserService) {
    fun createSession(token: String) {
        val user = jwtService.extractEmail(token)?.let { userService.findByEmail(it) }
            ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found")
        userService.updateSessions(
            Session(
                token = token,
                expirationDate = jwtService.extractExpiration(token).toInstant().atZone(ZoneId.systemDefault())
                    .toLocalDateTime(),
                id = UUID.randomUUID()
            ),
            user
        )
    }
}