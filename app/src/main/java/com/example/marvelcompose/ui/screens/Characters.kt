package com.example.marvelcompose.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.marvelcompose.data.entities.Character
import com.example.marvelcompose.data.repositories.CharactersRepository
import com.example.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.example.marvelcompose.ui.screens.common.MarvelItemListScreen

@Composable
fun CharacterScreen(onClick: (Character) -> Unit) {
    var characterState by remember { mutableStateOf(emptyList<Character>()) }

    LaunchedEffect(Unit) {
        characterState = CharactersRepository.get()
    }
    MarvelItemListScreen(
        items = characterState,
        onClick = onClick
    )
}

@Composable
fun CharacterDetailScreen(characterId: Int) {
    var characterState by remember { mutableStateOf<Character?>(null) }

    LaunchedEffect(Unit) {
        characterState = CharactersRepository.find(characterId)
    }

    characterState?.let { character ->
        MarvelItemDetailScreen(character)
    }
}