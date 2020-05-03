package com.test.b2schoolarithmetic.data.domain.user

import com.test.b2schoolarithmetic.data.remote.dto.RegisterUserDto

data class RegisterUser(
    val firstName: String,
    val lastName: String,
    val password: String,
    val userName: String
)

fun RegisterUser.toDto() = RegisterUserDto(firstName, lastName, password, userName)