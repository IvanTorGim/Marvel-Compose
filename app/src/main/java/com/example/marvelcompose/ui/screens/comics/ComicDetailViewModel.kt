package com.example.marvelcompose.ui.screens.comics

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelcompose.data.entities.Comic
import com.example.marvelcompose.data.repositories.ComicsRepository
import com.example.marvelcompose.ui.navigation.NavArg
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ComicDetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val id = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0
    private var _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(loading = true) }
            _state.update {
                it.copy(
                    comic = ComicsRepository.find(id),
                    loading = false
                )
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val comic: Comic? = null
    )
}