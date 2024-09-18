package com.example.marvelcompose.ui.screens.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.marvelcompose.data.model.Character
import com.example.marvelcompose.data.repositories.CharactersRepository

@Composable
fun CharacterScreen(onClick: (Character) -> Unit) {
    var characterState by rememberSaveable { mutableStateOf(emptyList<Character>()) }

    LaunchedEffect(true) {
        characterState = CharactersRepository.getCharacters()
    }
    CharactersScreen(
        characters = characterState,
        onClick = onClick
    )
}

@Composable
fun CharactersScreen(characters: List<Character>, onClick: (Character) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(characters) {
            CharacterItem(
                character = it,
                modifier = Modifier.clickable { onClick(it)}
            )
        }
    }
}

@Composable
fun CharacterItem(character: Character, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        Card {
            AsyncImage(
                model = character.thumbnail,
                contentDescription = character.name,
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = character.name,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2,
            modifier = Modifier.padding(8.dp, 16.dp)
        )
    }
}