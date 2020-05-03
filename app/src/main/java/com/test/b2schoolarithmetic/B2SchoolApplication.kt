package com.test.b2schoolarithmetic

import android.app.Application
import timber.log.Timber

class B2SchoolApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}