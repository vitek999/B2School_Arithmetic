package com.test.b2schoolarithmetic.presentation

import androidx.lifecycle.*
import com.test.b2schoolarithmetic.data.Result
import com.test.b2schoolarithmetic.data.remote.dto.ExerciseDto
import com.test.b2schoolarithmetic.data.remote.dto.UserTestResultDto
import com.test.b2schoolarithmetic.data.repository.ExerciseRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

class LevelViewModel(
    private val levelId: Long,
    private val exerciseRepository: ExerciseRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSuccessEvent = MutableLiveData<Event<Unit>>()
    val isSuccessEvent: LiveData<Event<Unit>> = _isSuccessEvent

    private val questions: MutableList<ExerciseDto> = mutableListOf()

    private val _currentQuestionNumber = MutableLiveData<Int>().apply { value = -1 }
    val currentQuestionNumber = _currentQuestionNumber

    private val _currentQuestion = MutableLiveData<ExerciseDto>()
    val currentQuestion: LiveData<ExerciseDto> = _currentQuestion

    private val answers = mutableListOf<Long>()

    private var startTime: Long = 0

    private var endTime: Long = 0

    fun fetchQuestion() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = exerciseRepository.getExcersises(levelId)
                if (result is Result.Success) {
                    questions.clear()
                    answers.clear()
                    Timber.d(result.data.toString())
                    questions.addAll(result.data)
                    _currentQuestion.value = result.data[0]
                }
            } catch (e: Exception) {

            }
            _isLoading.value = false
        }
    }

    private fun sendAnswers() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = exerciseRepository.sendResult(
                UserTestResultDto(answers, "", "", levelId)
            )
            if(result is Result.Success) _isSuccessEvent.value = Event(Unit)
            _isLoading.value = false
        }
    }

    fun start() {
        startTime = Calendar.getInstance().timeInMillis
        nextQuestion()
    }

    fun setAnswer(answerId: Long) {
        answers.add(answerId)
        nextQuestion()
    }

    private fun nextQuestion() {
        _currentQuestionNumber.value = _currentQuestionNumber.value!!.plus(1)
        if (_currentQuestionNumber.value as Int >= questions.size) {
            Timber.d(answers.toString())
            endTime = Calendar.getInstance().timeInMillis
            sendAnswers()
        } else {
            _currentQuestion.value = questions[_currentQuestionNumber.value as Int]
        }
    }

    class Factory(private val levelId: Long, private val exerciseRepository: ExerciseRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LevelViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LevelViewModel(levelId, exerciseRepository) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}