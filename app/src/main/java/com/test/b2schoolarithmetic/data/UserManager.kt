package com.test.b2schoolarithmetic.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class UserManager(private val application: Application) {
    private val preferences: SharedPreferences =
        application.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    var token : String
        get() {
            return preferences.getString(PREFERENCES_AUTH, "")!!
        }
        set(value) {
            preferences.edit().putString(PREFERENCES_AUTH, value).apply()
        }

    companion object {
        private const val PREFERENCES_NAME = "user:preferences"
        private const val PREFERENCES_AUTH = "user:preferences:auth"
    }
}