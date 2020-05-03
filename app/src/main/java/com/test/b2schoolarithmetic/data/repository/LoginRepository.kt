package com.test.b2schoolarithmetic.data.repository

import com.test.b2schoolarithmetic.data.Result

interface LoginRepository {
    suspend fun login(phone: String, password: String) : Result<Unit>
}