package ru.hse.auth.app.mappers

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import ru.hse.auth.app.data.entities.User
import ru.hse.auth.app.dtos.SignupUserDto

@Mapper(componentModel = "spring")
interface SignupUserDtoToUserEntityMapper {
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "sessions", expression = "java(java.util.Collections.emptyList())")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    fun map(signupUserDto: SignupUserDto): User
}