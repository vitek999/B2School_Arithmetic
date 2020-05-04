package com.test.b2schoolarithmetic.data.repository

import com.test.b2schoolarithmetic.data.remote.dto.ThemeDto
import com.test.b2schoolarithmetic.data.Result
import com.test.b2schoolarithmetic.data.remote.dto.ExerciseDto
import com.test.b2schoolarithmetic.data.remote.dto.UserTestResultDto

interface ExerciseRepository {
    suspend fun getAllThemes() : Result<List<ThemeDto>>

    suspend fun getExcersises(levelId: Long): Result<List<ExerciseDto>>

    suspend fun sendResult(result: UserTestResultDto) : Result<Unit>
}