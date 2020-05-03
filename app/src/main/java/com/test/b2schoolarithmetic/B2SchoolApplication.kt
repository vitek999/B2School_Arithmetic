package com.test.b2schoolarithmetic

import android.app.Application
import com.test.b2schoolarithmetic.data.dataKodeinModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.description
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import timber.log.Timber

class B2SchoolApplication : Application(), KodeinAware {

    override val kodein: Kodein = Kodein {
        import(dataKodeinModule)
        bind<B2SchoolApplication>() with provider { this@B2SchoolApplication }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.d(kodein.container.tree.bindings.description())
    }
}