package com.test.b2schoolarithmetic.data.remote.endpoints

import com.test.b2schoolarithmetic.data.remote.dto.RegisterUserDto
import com.test.b2schoolarithmetic.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface UserEndpoints {

    @GET("api/users/login")
    suspend fun login() : Response<UserDto>

    @POST("api/users/registration/parent")
    suspend fun registerParent(parent: RegisterUserDto) : Response<Unit>

    @POST("api/users/registration/student")
    suspend fun registerStudent(student: RegisterUserDto) : Response<Unit>

    @POST("api/users/registration/teacher")
    suspend fun registerTeacher(teacher: RegisterUserDto) : Response<Unit>
}