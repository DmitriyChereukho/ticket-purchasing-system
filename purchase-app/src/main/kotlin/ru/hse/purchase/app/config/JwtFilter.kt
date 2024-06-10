package ru.hse.purchase.app.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.reactive.function.client.WebClient

@Component
class JwtFilter(
) : OncePerRequestFilter() {
    private val webClient = WebClient.create()

    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {

        val authorizationHeader: String? = request.getHeader("Authorization")

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val token = authorizationHeader.substringAfter("Bearer ")

        val userDetails = webClient.get()
            .uri("http://ticket-purchasing-system-auth:8081/validate")
            .header("Authorization", "Bearer $token")
            .retrieve()
            .bodyToMono(CustomUserDetails::class.java)
            .block()

        if (userDetails != null && SecurityContextHolder.getContext().authentication == null) {
            updateContext(userDetails, request)
            filterChain.doFilter(request, response)
        }
    }

    fun updateContext(userDetails: UserDetails, request: HttpServletRequest) {
        val authToken = UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.authorities
        )
        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)

        SecurityContextHolder.getContext().authentication = authToken
    }

}