package com.test.b2schoolarithmetic.data.repository

import com.test.b2schoolarithmetic.data.Result
import com.test.b2schoolarithmetic.data.remote.dto.UserDto
import com.test.b2schoolarithmetic.data.remote.endpoints.UserEndpoints
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val userEndpoints: UserEndpoints,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserRepository {
    override suspend fun getUserInfo(): Result<UserDto> = withContext(ioDispatcher) {
        return@withContext try {
            val response = userEndpoints.login()
            if(response.code() == 200) Result.Success(response.body()!!) else Result.Error(Exception(""))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}