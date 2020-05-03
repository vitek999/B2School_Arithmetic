package com.test.b2schoolarithmetic.presentation

import androidx.lifecycle.*
import com.test.b2schoolarithmetic.R
import com.test.b2schoolarithmetic.data.Result
import com.test.b2schoolarithmetic.data.remote.dto.ThemeDto
import com.test.b2schoolarithmetic.data.repository.ExerciseRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(private val exerciseRepository: ExerciseRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean> = _isLoading

    private val _themes = MutableLiveData<List<ThemeDto>>().apply { value = emptyList() }
    val themes: LiveData<List<ThemeDto>> = _themes

    private val _errorEvent = MutableLiveData<Event<Int>>()
    val errorEvent: LiveData<Event<Int>> = _errorEvent

    fun fetchThemes() {
        viewModelScope.launch {
            _isLoading.value = true
            when (val result = exerciseRepository.getAllThemes()) {
                is Result.Success -> {
                    Timber.d(result.data.toString())
                    _themes.value = result.data
                }
                is Result.Error -> _errorEvent.value = Event(R.string.error_themes_loading)
            }
            _isLoading.value = false
        }
    }

    class Factory(private val exerciseRepository: ExerciseRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(exerciseRepository) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}