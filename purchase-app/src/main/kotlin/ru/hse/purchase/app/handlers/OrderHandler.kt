package ru.hse.purchase.app.handlers

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.hse.purchase.app.enums.OrderStatus
import ru.hse.purchase.app.services.OrderService

@Component
class OrderHandler(private val orderService: OrderService) {

    @Scheduled(fixedDelay = 5000)
    fun processOrders() {
        val orderToCheck = orderService.findByStatus(OrderStatus.CHECK) ?: return

        val random = (0..1).random()

        if (random == 0) {
            orderService.updateStatus(orderToCheck.id, OrderStatus.SUCCESS)
        } else {
            orderService.updateStatus(orderToCheck.id, OrderStatus.REJECTED)
        }
    }
}