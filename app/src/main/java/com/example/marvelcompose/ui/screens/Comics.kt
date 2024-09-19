package com.example.marvelcompose.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.marvelcompose.data.entities.Comic
import com.example.marvelcompose.data.repositories.ComicsRepository
import com.example.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.example.marvelcompose.ui.screens.common.MarvelItemListScreen

@Composable
fun ComicsScreen(onClick: (Comic) -> Unit) {
    var comicsState by remember { mutableStateOf(emptyList<Comic>()) }
    LaunchedEffect(Unit) { comicsState = ComicsRepository.get() }
    MarvelItemListScreen(
        items = comicsState,
        onClick = onClick
    )
}

@Composable
fun ComicDetailScreen(comicId: Int, onUpClick: () -> Unit) {
    var comicState by remember { mutableStateOf<Comic?>(null) }
    LaunchedEffect(Unit) { comicState = ComicsRepository.find(comicId) }
    comicState?.let {
        MarvelItemDetailScreen(
            marvelItem = it,
            onUpClick = onUpClick
        )
    }
}