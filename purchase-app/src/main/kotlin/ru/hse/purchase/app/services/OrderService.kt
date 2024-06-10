package ru.hse.purchase.app.services

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ru.hse.purchase.app.data.entities.Order
import ru.hse.purchase.app.data.repositories.OrderRepository
import ru.hse.purchase.app.dtos.OrderInfoDto
import ru.hse.purchase.app.dtos.OrderPreviewDto
import ru.hse.purchase.app.enums.OrderStatus
import ru.hse.purchase.app.validators.OrderValidator
import java.util.*

@Service
class OrderService(private val orderRepository: OrderRepository, private val orderValidator: OrderValidator) {
    fun createOrder(order: Order) {
        orderValidator.validateOrder(order)

        val email = SecurityContextHolder.getContext().authentication.name
        orderRepository.save(order.copy(email = email))
    }

    fun findByStatus(status: OrderStatus): Order? {
        return orderRepository.findAll().find { it.status == status }
    }

    fun updateStatus(id: UUID, newStatus: OrderStatus) {
        val order = orderRepository.findById(id).get()
        orderRepository.save(order.copy(status = newStatus))
    }

    fun getOrders(): List<OrderPreviewDto> {
        return orderRepository.findAll().map {
            OrderPreviewDto(
                id = it.id,
                email = it.email,
            )
        }
    }

    fun getOrderInfo(id: UUID): OrderInfoDto {
        val order = orderRepository.findById(id).get()

        return OrderInfoDto(
            id = order.id,
            fromStation = order.fromStationId,
            toStation = order.toStationId,
            email = order.email,
            status = order.status,
            createdAt = order.createdAt,
        )
    }

}