package ru.hse.purchase.app.data.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.hse.purchase.app.data.entities.Order
import java.util.*

interface OrderRepository : JpaRepository<Order, UUID>