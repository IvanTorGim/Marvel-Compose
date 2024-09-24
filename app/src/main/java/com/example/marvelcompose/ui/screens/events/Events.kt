package com.example.marvelcompose.ui.screens.events

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.marvelcompose.data.entities.Event
import com.example.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.example.marvelcompose.ui.screens.common.MarvelItemListScreen

@Composable
fun EventsScreen(
    viewModel: EventsViewModel = viewModel(),
    onClick: (Event) -> Unit
) {
    val state by viewModel.state.collectAsState()
    MarvelItemListScreen(
        marvelItems = state.items,
        loading = state.loading,
        onClick = onClick
    )
}

@Composable
fun EventDetailScreen(viewModel: EventDetailViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    MarvelItemDetailScreen(
        loading = state.loading,
        marvelItem = state.event
    )
}
