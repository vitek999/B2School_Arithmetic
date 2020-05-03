package com.test.b2schoolarithmetic.data

import java.lang.Exception

/**
 * A generic class that holds a value with its loading status
 * @param <T>
 */
sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when(this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

/**
 * 'true' if [Result] is of type [Result.Success] & holds non-null [Result.Success.data]
 */
val Result<*>.succeeded
    get() = this is Result.Success && data != null