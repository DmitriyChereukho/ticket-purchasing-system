package ru.hse.auth.app.controllers

import org.springframework.web.bind.annotation.*
import ru.hse.auth.app.dtos.SignupUserDto
import ru.hse.auth.app.mappers.SignupUserDtoToUserEntityMapper
import ru.hse.auth.app.services.UserService

@RestController
@RequestMapping("/signup")
class RegisterController(
    private val userService: UserService,
    private val signupUserDtoToUserEntityMapper: SignupUserDtoToUserEntityMapper
) {
    @PostMapping
    fun registerUser(@RequestBody signupUserDto: SignupUserDto) {
        userService.createUser(signupUserDtoToUserEntityMapper.map(signupUserDto))
    }
}