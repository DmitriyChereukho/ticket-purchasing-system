package ru.hse.auth.app.data.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.hse.auth.app.data.entities.User

@Repository
interface UserRepository : JpaRepository<User, Int>