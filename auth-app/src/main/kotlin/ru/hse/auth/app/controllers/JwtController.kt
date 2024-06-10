package ru.hse.auth.app.controllers

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.hse.auth.app.services.JwtService

@RestController
class JwtController(private val jwtService: JwtService) {
    @GetMapping("/validate")
    fun validate(): UserDetails {
        return jwtService.isValidUser()
    }
}