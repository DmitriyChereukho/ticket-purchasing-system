package ru.hse.auth.app.services

import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import ru.hse.auth.app.data.entities.Session
import ru.hse.auth.app.data.entities.User
import ru.hse.auth.app.data.repositories.UserRepository
import ru.hse.auth.app.dtos.UserInfoDto
import ru.hse.auth.app.validators.UserValidator

@Service
class UserService(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder,
    private val userValidator: UserValidator
) {
    fun findByEmail(email: String): User? {
        return userRepository.findAll().find { it.email == email }
    }

    fun createUser(user: User): User {
        if (findByEmail(user.email) != null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this email already exists")
        }

        userValidator.validateSignUp(user.username, user.email, user.password)

        return userRepository.save(user.copy(password = encoder.encode(user.password)))
    }

    fun updateSessions(session: Session, user: User) {
        val newSessions = user.sessions.toMutableList()
        newSessions.add(session)
        userRepository.save(user.copy(sessions = newSessions.toList()))
    }

    fun getThisSessionUser(): User {
        val user = findByEmail(
            SecurityContextHolder.getContext().authentication.name
        ) ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found")

        return user
    }

    fun getUserInfo(): UserInfoDto {
        val user = getThisSessionUser()
        return UserInfoDto(user.id, user.username, user.email, user.createdAt)
    }
}