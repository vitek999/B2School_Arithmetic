package com.test.b2schoolarithmetic.data.repository

import com.test.b2schoolarithmetic.data.Result
import com.test.b2schoolarithmetic.data.domain.user.RegisterUser
import com.test.b2schoolarithmetic.data.domain.user.UserType

interface LoginRepository {
    suspend fun login(phone: String, password: String) : Result<Unit>

    suspend fun register(userType: UserType, newUser: RegisterUser) : Result<Unit>
}