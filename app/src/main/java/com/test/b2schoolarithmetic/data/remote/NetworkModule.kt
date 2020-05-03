package com.test.b2schoolarithmetic.data.remote

import com.test.b2schoolarithmetic.data.remote.endpoints.ExerciseEndpoints
import com.test.b2schoolarithmetic.data.remote.endpoints.UserEndpoints
import com.test.b2schoolarithmetic.data.remote.iterceptors.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkKodeinModule = Kodein.Module("NetworkKodeinModule") {
    constant(tag = "BASE_URL") with "http://79.170.167.31:8080/b2school_server-0.0.1/"
    bind<AuthInterceptor>() with provider { AuthInterceptor(instance()) }
    bind<OkHttpClient>() with singleton { provideOkHttpClient(instance()) }
    bind<Retrofit>() with singleton { provideRetrofit(instance(tag = "BASE_URL"), instance()) }
    bind<UserEndpoints>() with singleton { instance<Retrofit>().create(UserEndpoints::class.java) }
    bind<ExerciseEndpoints>() with singleton { instance<Retrofit>().create(ExerciseEndpoints::class.java) }
}

private fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()

    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    builder.addInterceptor(logging)
    builder.addInterceptor(authInterceptor)
    builder.connectTimeout(60 * 1000.toLong(), TimeUnit.MILLISECONDS)
        .readTimeout(60 * 1000.toLong(), TimeUnit.MILLISECONDS)
    return builder.build()
}

private fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}