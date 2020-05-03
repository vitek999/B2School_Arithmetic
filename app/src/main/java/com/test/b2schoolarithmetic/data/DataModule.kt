package com.test.b2schoolarithmetic.data

import com.test.b2schoolarithmetic.data.remote.networkKodeinModule
import com.test.b2schoolarithmetic.data.repository.LoginRepository
import com.test.b2schoolarithmetic.data.repository.LoginRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val dataKodeinModule = Kodein.Module("DataKodeinModule"){
    import(networkKodeinModule)
    bind<CoroutineDispatcher>() with provider { Dispatchers.IO }
    bind<UserManager>() with singleton { UserManager(instance()) }
    bind<LoginRepository>() with singleton { LoginRepositoryImpl(instance(), instance(), instance()) }
}