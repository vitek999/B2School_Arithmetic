package com.test.b2schoolarithmetic.data.repository

import com.test.b2schoolarithmetic.data.Result
import com.test.b2schoolarithmetic.data.remote.dto.ExerciseDto
import com.test.b2schoolarithmetic.data.remote.dto.ThemeDto
import com.test.b2schoolarithmetic.data.remote.dto.UserTestResultDto
import com.test.b2schoolarithmetic.data.remote.endpoints.ExerciseEndpoints
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseRepositoryImpl(
    private val exerciseEndpoints: ExerciseEndpoints,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ExerciseRepository {
    override suspend fun getAllThemes(): Result<List<ThemeDto>> = withContext(ioDispatcher) {
        return@withContext try {
            val result = exerciseEndpoints.getAllThemes()
            Result.Success(result.body().orEmpty())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getExcersises(levelId: Long): Result<List<ExerciseDto>> = withContext(ioDispatcher) {
        return@withContext try {
            val result = exerciseEndpoints.getExeExercise(levelId)
            Result.Success(result.body().orEmpty())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun sendResult(result: UserTestResultDto): Result<Unit> = withContext(ioDispatcher) {
        return@withContext try {
            val response = exerciseEndpoints.sendResult(result)
            if(response.code() == 201) Result.Success(Unit) else Result.Error(Exception("wrong answers"))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}