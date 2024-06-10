package ru.hse.purchase.app.validators

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import ru.hse.purchase.app.data.entities.Order

@Component
class OrderValidator {
    fun validateOrder(order: Order) {
        if(order.toStationId == order.fromStationId) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "From and to stations must be different")
        }

        if(order.toStationId < 1 || order.fromStationId < 1) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Station ids must be at least 1")
        }

        if(order.toStationId > 10 || order.fromStationId > 10) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Station ids must be at most 10")
        }
    }
}