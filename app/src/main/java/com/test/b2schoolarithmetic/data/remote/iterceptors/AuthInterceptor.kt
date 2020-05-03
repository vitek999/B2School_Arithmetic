package com.test.b2schoolarithmetic.data.remote.iterceptors

import com.test.b2schoolarithmetic.data.UserManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val userManager: UserManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val builder = chain.request().newBuilder()
        if(!chain.request().url.toUrl().path.contains("registration")) {
            builder.addHeader("Authorization", userManager.token)
        }
        return chain.proceed(builder.build())
    }

}