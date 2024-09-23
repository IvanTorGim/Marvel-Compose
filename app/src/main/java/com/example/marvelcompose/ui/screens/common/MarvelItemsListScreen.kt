package com.example.marvelcompose.ui.screens.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.marvelcompose.data.entities.MarvelItem

@Composable
fun <T : MarvelItem> MarvelItemListScreen(items: List<T>, onClick: (T) -> Unit) {
    MarvelItemsList(
        items = items,
        onClick = onClick
    )
}

@Composable
fun <T : MarvelItem> MarvelItemsList(
    items: List<T>,
    onClick: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        contentPadding = PaddingValues(4.dp),
        modifier = modifier
    ) {
        items(items) {
            MarvelListItem(
                marvelItem = it,
                modifier = Modifier.clickable { onClick(it) }
            )
        }
    }
}