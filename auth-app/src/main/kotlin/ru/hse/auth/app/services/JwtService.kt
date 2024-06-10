package ru.hse.auth.app.services

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import ru.hse.auth.app.config.JwtProperties
import java.util.*

@Service
class JwtService(
    jwtProperties: JwtProperties,
    private val userDetailsService: AuthUserDetailsService,
    private val userService: UserService
) {
    private val secretKey = Keys.hmacShaKeyFor(jwtProperties.key.toByteArray())

    fun generate(
        userDetails: UserDetails, expirationDate: Date, additionalClaims: Map<String, Any> = emptyMap()
    ): String {
        return Jwts.builder()
            .claims()
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(expirationDate)
            .add(additionalClaims)
            .and().signWith(secretKey)
            .compact()
    }

    fun extractEmail(token: String): String? {
        return getAllClaims(token).subject
    }

    fun extractExpiration(token: String): Date {
        return getAllClaims(token).expiration
    }

    fun isExpired(token: String): Boolean {
        return extractExpiration(token).before(Date(System.currentTimeMillis()))
    }

    fun isValid(token: String, userDetails: UserDetails): Boolean {
        val email = extractEmail(token)
        return email == userDetails.username && !isExpired(token)
    }

    private fun getAllClaims(token: String): Claims {

        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload
    }

    fun isValidUser(): UserDetails {
        return userDetailsService.loadUserByUsername(SecurityContextHolder.getContext().authentication.name)
    }
}