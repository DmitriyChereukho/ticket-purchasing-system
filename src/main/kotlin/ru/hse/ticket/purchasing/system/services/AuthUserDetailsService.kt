package ru.hse.ticket.purchasing.system.services

import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.User
import org.springframework.web.server.ResponseStatusException

@Service
class AuthUserDetailsService(private val userService: UserService) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userService.findByEmail(username)?.mapToUserDetails()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
    }

    private fun ru.hse.ticket.purchasing.system.data.entities.User.mapToUserDetails(): UserDetails {
        return User.builder()
            .username(this.email)
            .password(this.password)
            .build()
    }
}