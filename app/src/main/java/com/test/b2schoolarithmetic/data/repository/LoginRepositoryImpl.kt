package com.test.b2schoolarithmetic.data.repository


import com.test.b2schoolarithmetic.data.Result
import com.test.b2schoolarithmetic.data.UserManager
import com.test.b2schoolarithmetic.data.remote.endpoints.UserEndpoints
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Credentials
import timber.log.Timber

class LoginRepositoryImpl(
    private val userEndpoints: UserEndpoints,
    private val userManager: UserManager,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LoginRepository {
    override suspend fun login(phone: String, password: String): Result<Unit> = withContext(ioDispatcher) {
        userManager.token = Credentials.basic(phone, password)
        return@withContext try {
            userEndpoints.login()
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e)
            Result.Error(e)
        }
    }
}
