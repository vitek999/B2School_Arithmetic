package com.test.b2schoolarithmetic.presentation

import androidx.lifecycle.*
import com.test.b2schoolarithmetic.data.repository.LoginRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean> = _isLoading

    private val _authorizedGameEvent = MutableLiveData<Event<Unit>>()
    val authorizedGameEvent: LiveData<Event<Unit>> = _authorizedGameEvent

    private val _errorCreateEvent = MutableLiveData<Event<Int>>()
    val errorEvent: LiveData<Event<Int>> = _errorCreateEvent

    fun login(phone: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            loginRepository.login(phone, password)
            _authorizedGameEvent.value = Event(Unit)
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