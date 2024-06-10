package ru.hse.auth.app.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.hse.auth.app.dtos.AuthenticationRequest
import ru.hse.auth.app.dtos.AuthenticationResponse
import ru.hse.auth.app.services.AuthenticationService


@RestController
@RequestMapping("/login")
class AuthController(@Autowired private val authenticationService: AuthenticationService) {

    @PostMapping("")
    fun authenticate(@RequestBody authRequest: AuthenticationRequest) : AuthenticationResponse {
        return authenticationService.authenticate(authRequest)
    }

}