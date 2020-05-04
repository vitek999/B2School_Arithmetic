package com.test.b2schoolarithmetic.data.remote.endpoints

import com.test.b2schoolarithmetic.data.remote.dto.ExerciseDto
import com.test.b2schoolarithmetic.data.remote.dto.ThemeDto
import com.test.b2schoolarithmetic.data.remote.dto.UserTestResultDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ExerciseEndpoints {
    @GET("api/theme/all")
    suspend fun getAllThemes(): Response<List<ThemeDto>>

    @GET("api/exercise/{levelId}")
    suspend fun getExeExercise(@Path("levelId") levelId: Long): Response<List<ExerciseDto>>

    @POST("api/level")
    suspend fun sendResult(@Body result: UserTestResultDto) : Response<Unit>
}