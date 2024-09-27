package com.example.marvelcompose.ui.screens.events

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.example.marvelcompose.data.entities.Event
import com.example.marvelcompose.data.entities.Result
import com.example.marvelcompose.data.repositories.EventRepository
import com.example.marvelcompose.ui.navigation.NavArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: EventRepository
) : ViewModel() {

    private val id = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    private var _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(event = repository.find(id))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val event: Result<Event?> = Either.Right(null)
    )
}