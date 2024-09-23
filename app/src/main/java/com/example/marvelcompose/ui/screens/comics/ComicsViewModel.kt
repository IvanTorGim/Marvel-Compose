package com.example.marvelcompose.ui.screens.comics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelcompose.data.entities.Comic
import com.example.marvelcompose.data.repositories.ComicsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ComicsViewModel : ViewModel() {
    var state = Comic.Format.entries.associate { it to MutableStateFlow(UiState()) }

    fun formatRequested(format: Comic.Format) {
        val uiState = state.getValue(format)
        if (uiState.value.items.isNotEmpty()) return

        viewModelScope.launch {
            uiState.value = uiState.value.copy(loading = true)
            uiState.value = uiState.value.copy(
                items = ComicsRepository.get(format),
                loading = false
            )
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val items: List<Comic> = emptyList()
    )
}