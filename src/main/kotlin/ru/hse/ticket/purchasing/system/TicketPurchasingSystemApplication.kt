package ru.hse.ticket.purchasing.system

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TicketPurchasingSystemApplication

fun main(args: Array<String>) {
    runApplication<TicketPurchasingSystemApplication>(*args)
}
