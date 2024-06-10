package ru.hse.auth.app.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.hse.auth.app.dtos.UserInfoDto
import ru.hse.auth.app.services.UserService

@RestController
class UserController(private val userService: UserService) {
    @GetMapping("/user")
    fun getUserInfo() : UserInfoDto = userService.getUserInfo()
}