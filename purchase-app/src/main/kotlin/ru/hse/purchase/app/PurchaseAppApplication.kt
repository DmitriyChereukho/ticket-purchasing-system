package ru.hse.purchase.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PurchaseAppApplication

fun main(args: Array<String>) {
    runApplication<PurchaseAppApplication>(*args)
}
