package com.example.geoquiz.quiz


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.example.geoquiz.data.questionBank


class QuizViewModel : ViewModel() {
    data class UiState(
        val currentIndex: Int = 0,
        val answered: Boolean = false,
        val correctCount: Int = 0,
        val showResult: Boolean = false
    )


    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState


    val totalQuestions = questionBank.size


    fun currentQuestionText(): String = questionBank[_uiState.value.currentIndex].text


    fun answer(userAnswer: Boolean) {
        val idx = _uiState.value.currentIndex
        val isCorrect = questionBank[idx].answer == userAnswer
        _uiState.update { s -> s.copy(answered = true, correctCount = s.correctCount + if (isCorrect) 1 else 0) }
    }


    fun next() {
        if (isLastQuestion()) return
        _uiState.update { s -> s.copy(currentIndex = s.currentIndex + 1, answered = false) }
    }


    fun isLastQuestion(): Boolean = _uiState.value.currentIndex == totalQuestions - 1
}