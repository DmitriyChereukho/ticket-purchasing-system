package ru.hse.purchase.app.mapppers

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import ru.hse.purchase.app.data.entities.Order
import ru.hse.purchase.app.dtos.OrderDto

@Mapper
interface OrderDtoToOrderEntityMapper {
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "status", expression = "java(ru.hse.purchase.app.enums.OrderStatus.CHECK)")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "email", constant = "")
    fun map(orderDto: OrderDto): Order
}