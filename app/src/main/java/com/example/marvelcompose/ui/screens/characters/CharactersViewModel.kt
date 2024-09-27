package com.example.marvelcompose.ui.screens.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.data.entities.Result
import com.example.marvelcompose.data.repositories.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    repository: CharactersRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(items = repository.get())
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val items: Result<List<Character>> = Either.Right(emptyList())
    )
}