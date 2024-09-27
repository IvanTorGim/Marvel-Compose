package com.example.marvelcompose.ui.screens.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.example.marvelcompose.data.entities.Event
import com.example.marvelcompose.data.entities.Result
import com.example.marvelcompose.data.repositories.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    repository: EventRepository
) : ViewModel() {

    private var _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(items = repository.get())
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val items: Result<List<Event>> = Either.Right(emptyList())
    )
}