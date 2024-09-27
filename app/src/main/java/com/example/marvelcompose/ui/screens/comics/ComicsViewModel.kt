package com.example.marvelcompose.ui.screens.comics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.example.marvelcompose.data.entities.Comic
import com.example.marvelcompose.data.entities.Result
import com.example.marvelcompose.data.repositories.ComicsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    private val repository: ComicsRepository
) : ViewModel() {
    var state = Comic.Format.entries.associate { it to MutableStateFlow(UiState()) }

    fun formatRequested(format: Comic.Format) {
        val uiState = state.getValue(format)
        val comics = uiState.value.items
        if (comics is Either.Right && comics.value.isEmpty()) {
            viewModelScope.launch {
                uiState.value = UiState(loading = true)
                uiState.value = UiState(items = repository.get(format))
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val items: Result<List<Comic>> = Either.Right(emptyList())
    )
}