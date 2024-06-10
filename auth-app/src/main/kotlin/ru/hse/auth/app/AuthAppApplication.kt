package ru.hse.auth.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AuthAppApplication

fun main(args: Array<String>) {
    runApplication<AuthAppApplication>(*args)
}