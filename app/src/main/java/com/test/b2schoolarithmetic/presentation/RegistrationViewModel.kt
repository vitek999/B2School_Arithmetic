package com.test.b2schoolarithmetic.presentation

import androidx.lifecycle.*
import com.test.b2schoolarithmetic.data.domain.user.RegisterUser
import com.test.b2schoolarithmetic.data.domain.user.UserType
import com.test.b2schoolarithmetic.data.repository.LoginRepository
import com.test.b2schoolarithmetic.data.Result
import kotlinx.coroutines.launch

import com.test.b2schoolarithmetic.R

class RegistrationViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorEvent = MutableLiveData<Event<Int>>()
    val errorEvent: LiveData<Event<Int>> = _errorEvent

    private val _successRegistration = MutableLiveData<Event<Unit>>()
    val successRegistration: LiveData<Event<Unit>> = _successRegistration

    fun registration(userType: UserType, newUser: RegisterUser) {
        viewModelScope.launch {
            _isLoading.value = true
            when(loginRepository.register(userType, newUser)) {
                is Result.Success -> _successRegistration.value = Event(Unit)
                is Result.Error -> _errorEvent.value = Event(R.string.registration_wrong_data)
            }
            _isLoading.value = false
        }
    }

    class Factory(private val loginRepository: LoginRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return RegistrationViewModel(loginRepository) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")

        }
    }
}