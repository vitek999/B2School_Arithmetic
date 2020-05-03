package com.test.b2schoolarithmetic.data.remote.endpoints

import com.test.b2schoolarithmetic.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.GET

interface UserEndpoints {

    @GET("api/users/login")
    suspend fun login() : Response<UserDto>

}