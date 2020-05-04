package com.test.b2schoolarithmetic.data.repository

import com.test.b2schoolarithmetic.data.remote.dto.UserDto
import com.test.b2schoolarithmetic.data.Result

interface UserRepository {
    suspend fun getUserInfo() : Result<UserDto>
}