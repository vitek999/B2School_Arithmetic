package com.test.b2schoolarithmetic.presentation

import androidx.lifecycle.*
import com.test.b2schoolarithmetic.R
import com.test.b2schoolarithmetic.data.Result
import com.test.b2schoolarithmetic.data.remote.dto.UserDto
import com.test.b2schoolarithmetic.data.repository.UserRepository
import kotlinx.coroutines.launch

class AccountViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean> = _isLoading

    private val _user = MutableLiveData<UserDto>()
    val user: LiveData<UserDto> = _user

    private val _errorEvent = MutableLiveData<Event<Int>>()
    val errorEvent: LiveData<Event<Int>> = _errorEvent

    fun fetchAccountInfo() {
        viewModelScope.launch {
            _isLoading.value = true
            when(val result = userRepository.getUserInfo()) {
                is Result.Success -> _user.value = result.data
                is Result.Error -> _errorEvent.value = Event(R.string.error_with_account_loading)
            }
            _isLoading.value = false
        }
    }

    class Factory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AccountViewModel(userRepository) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}