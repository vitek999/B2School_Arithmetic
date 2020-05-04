package com.test.b2schoolarithmetic.presentation

import androidx.lifecycle.*
import com.test.b2schoolarithmetic.data.Result
import com.test.b2schoolarithmetic.data.remote.dto.ExerciseDto
import com.test.b2schoolarithmetic.data.remote.dto.UserTestResultDto
import com.test.b2schoolarithmetic.data.repository.ExerciseRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class LevelViewModel(
    private val levelId: Long,
    private val exerciseRepository: ExerciseRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSuccessEvent = MutableLiveData<Event<Boolean>>()
    val isSuccessEvent: LiveData<Event<Boolean>> = _isSuccessEvent

    private val questions: MutableList<ExerciseDto> = mutableListOf()

    private val _currentQuestionNumber = MutableLiveData<Int>().apply { value = -1 }
    val currentQuestionNumber = _currentQuestionNumber

    private val _dataLoadedEvent = MutableLiveData<Event<Unit>>()
    val dataLoadedEvent: LiveData<Event<Unit>> = _dataLoadedEvent

    private val _currentQuestion = MutableLiveData<ExerciseDto>()
    val currentQuestion: LiveData<ExerciseDto> = _currentQuestion

    private val _errorsCount = MutableLiveData<Int>().apply { value = 0 }
    val errorsCount: LiveData<Int> = _errorsCount

    var questionCount = 0
        private set

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
                    questionCount = result.data.size
                    _dataLoadedEvent.value = Event(Unit)
                }
            } catch (e: Exception) {

            }
            _isLoading.value = false
        }
    }

    private fun sendAnswers() {
        viewModelScope.launch {
            _isLoading.value = true
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val result = exerciseRepository.sendResult(
                UserTestResultDto(
                    answers,
                    dateFormat.format(Date(endTime)),
                    dateFormat.format(Date(startTime)),
                    levelId
                )
            )
            if (result is Result.Success) _isSuccessEvent.value = Event(_errorsCount.value == 0)
            _isLoading.value = false
        }
    }

    fun start() {
        startTime = Calendar.getInstance().timeInMillis
        nextQuestion()
    }

    fun setAnswer(answerId: Long, isCorrect: Boolean) {
        if (!isCorrect) _errorsCount.value = _errorsCount.value!!.plus(1)
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