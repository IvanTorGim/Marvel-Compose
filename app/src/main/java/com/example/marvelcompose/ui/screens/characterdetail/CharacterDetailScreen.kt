package com.example.marvelcompose.ui.screens.characterdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.marvelcompose.MarvelApp
import com.example.marvelcompose.data.model.Character
import com.example.marvelcompose.data.model.Reference
import com.example.marvelcompose.data.repositories.CharactersRepository

@Composable
fun CharacterDetailScreen(id: Int) {
    var characterState by remember { mutableStateOf<Character?>(null) }
    LaunchedEffect(true) {
        characterState = CharactersRepository.findCharacter(id)
    }
    characterState?.let { character ->
        CharacterDetailScreen(character)
    }
}

@Composable
fun CharacterDetailScreen(character: Character) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        item { Header(character) }
        section(icon = Icons.Default.Collections, name = "Series", references = character.series)
        section(icon = Icons.Default.Event, name = "Events", references = character.events)
        section(icon = Icons.Default.Book, name = "Comics", references = character.comics)
        section(icon = Icons.Default.Bookmark, name = "Stories", references = character.stories)
    }
}

fun LazyListScope.section(icon: ImageVector, name: String, references: List<Reference>) {
    if (references.isEmpty()) return
    item {
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
    items(references) { reference ->
        ListItem(
            headlineContent = {
                Text(text = reference.name)
            },
            leadingContent = {
                Icon(
                    imageVector = icon,
                    contentDescription = name
                )
            }
        )
    }

}

@Composable
fun Header(character: Character) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = character.thumbnail,
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}