package ru.hse.auth.app.services

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import ru.hse.auth.app.config.JwtProperties
import ru.hse.auth.app.dtos.AuthenticationRequest
import ru.hse.auth.app.dtos.AuthenticationResponse
import java.util.*

@Service
class AuthenticationService(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: AuthUserDetailsService,
    private val jwtService: JwtService,
    private val jwtProperties: JwtProperties,
    private val sessionService: SessionService
) {
    fun authenticate(authRequest: AuthenticationRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        )

        val user = userDetailsService.loadUserByUsername(authRequest.email)
        val accessToken = jwtService.generate(
            userDetails = user,
            expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
        )

        sessionService.createSession(accessToken)

        return AuthenticationResponse(accessToken)
    }

}