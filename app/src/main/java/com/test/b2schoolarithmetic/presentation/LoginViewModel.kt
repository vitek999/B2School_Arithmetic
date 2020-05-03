package com.test.b2schoolarithmetic.presentation

import androidx.lifecycle.*
import com.test.b2schoolarithmetic.data.Result
import com.test.b2schoolarithmetic.data.repository.LoginRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

import com.test.b2schoolarithmetic.R

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean> = _isLoading

    private val _authorizedGameEvent = MutableLiveData<Event<Unit>>()
    val authorizedGameEvent: LiveData<Event<Unit>> = _authorizedGameEvent

    private val _errorEvent = MutableLiveData<Event<Int>>()
    val errorEvent: LiveData<Event<Int>> = _errorEvent

    fun login(phone: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            when(loginRepository.login(phone, password)) {
                is Result.Success -> _authorizedGameEvent.value = Event(Unit)
                is Result.Error -> _errorEvent.value = Event(R.string.authorization_error)
            }
            _isLoading.value = false
        }
    }

    class Factory(private val loginRepository: LoginRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LoginViewModel(loginRepository) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}