package ru.hse.auth.app.validators

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import java.util.regex.Pattern

@Component
class UserValidator {
    private val emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$")
    private val passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")

    fun validateUsername(username: String): Boolean {
        return username.length >= 3 && username.all { it.isLetterOrDigit() }
    }

    fun validateEmail(email: String): Boolean {
        return emailPattern.matcher(email).matches()
    }

    fun validatePassword(password: String): Boolean {
        return password.length >= 8 && passwordPattern.matcher(password).matches()
    }

    fun validateSignUp(username: String, email: String, password: String) {
        if(!validateUsername(username)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid username" +
                    " (should contain only letters and digits and be at least 3 characters long)")
        }

        if(!validateEmail(email)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email")
        }

        if(!validatePassword(password)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid password" +
                    " (should contain at least one digit, one lowercase letter, one uppercase letter," +
                    " one special character and be at least 8 characters long)")
        }
    }
}