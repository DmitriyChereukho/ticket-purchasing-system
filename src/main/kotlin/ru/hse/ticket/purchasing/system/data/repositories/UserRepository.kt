package ru.hse.ticket.purchasing.system.data.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.hse.ticket.purchasing.system.data.entities.User

@Repository
interface UserRepository : JpaRepository<User, Int>