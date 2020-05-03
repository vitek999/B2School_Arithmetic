package com.test.b2schoolarithmetic.data.repository


import com.test.b2schoolarithmetic.data.Result
import com.test.b2schoolarithmetic.data.UserManager
import com.test.b2schoolarithmetic.data.domain.user.RegisterUser
import com.test.b2schoolarithmetic.data.domain.user.UserType
import com.test.b2schoolarithmetic.data.domain.user.toDto
import com.test.b2schoolarithmetic.data.remote.endpoints.UserEndpoints
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Credentials

class LoginRepositoryImpl(
    private val userEndpoints: UserEndpoints,
    private val userManager: UserManager,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LoginRepository {
    override suspend fun login(phone: String, password: String): Result<Unit> =
        withContext(ioDispatcher) {
            userManager.token = Credentials.basic(phone, password)
            val response = userEndpoints.login()
            return@withContext try {
                if (response.code() == 401) Result.Error(Exception("Unauthorized")) else Result.Success(Unit)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun register(userType: UserType, newUser: RegisterUser): Result<Unit> = withContext(ioDispatcher) {
        val response = when(userType) {
            UserType.PARENT -> userEndpoints.registerParent(newUser.toDto())
            UserType.STUDENT -> userEndpoints.registerStudent(newUser.toDto())
            UserType.TEACHER -> userEndpoints.registerTeacher(newUser.toDto())
        }
        return@withContext try {
            if(response.code() == 409) Result.Error(Exception("Wrong registration params")) else Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
