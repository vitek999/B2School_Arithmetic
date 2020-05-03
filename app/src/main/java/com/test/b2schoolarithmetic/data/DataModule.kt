package com.test.b2schoolarithmetic.data

import com.test.b2schoolarithmetic.data.remote.networkKodeinModule
import com.test.b2schoolarithmetic.data.repository.LoginRepository
import com.test.b2schoolarithmetic.data.repository.LoginRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val dataKodeinModule = Kodein.Module("DataKodeinModule"){
    import(networkKodeinModule)
    bind<UserManager>() with singleton { UserManager(instance()) }
    bind<LoginRepository>() with singleton { LoginRepositoryImpl(instance(), instance(), instance()) }
}