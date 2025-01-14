package com.example.marvelcompose.ui.screens.characters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.example.marvelcompose.ui.screens.common.MarvelItemListScreen

@Composable
fun CharacterScreen(
    viewModel: CharactersViewModel = hiltViewModel(),
    onClick: (Character) -> Unit
) {
    val state by viewModel.state.collectAsState()
    MarvelItemListScreen(
        marvelItems = state.items,
        loading = state.loading,
        onClick = onClick
    )
}

@Composable
fun CharacterDetailScreen(viewModel: CharacterDetailViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    MarvelItemDetailScreen(
        loading = state.loading,
        marvelItem = state.character
    )
}