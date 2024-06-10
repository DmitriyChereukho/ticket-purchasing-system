package ru.hse.purchase.app.controllers

import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.hse.purchase.app.data.entities.Order
import ru.hse.purchase.app.dtos.OrderDto
import ru.hse.purchase.app.dtos.OrderInfoDto
import ru.hse.purchase.app.dtos.OrderPreviewDto
import ru.hse.purchase.app.enums.OrderStatus
import ru.hse.purchase.app.services.OrderService

@RestController
class OrderController(
    private val orderService: OrderService,
) {
    @PostMapping("/order")
    fun createOrder(@RequestBody order: OrderDto) {
        orderService.createOrder(
            Order(
                fromStationId = order.fromStationId,
                toStationId = order.toStationId,
                email = "",
                status = OrderStatus.CHECK,
                createdAt = java.time.LocalDateTime.now(),
                id = java.util.UUID.randomUUID()
            )
        )
    }

    @GetMapping("/order")
    fun getOrders(): List<OrderPreviewDto> {
        return orderService.getOrders()
    }

    @GetMapping("/order/{id}")
    fun getOrderInfo(@PathVariable id: java.util.UUID): OrderInfoDto {
        return orderService.getOrderInfo(id)
    }
}