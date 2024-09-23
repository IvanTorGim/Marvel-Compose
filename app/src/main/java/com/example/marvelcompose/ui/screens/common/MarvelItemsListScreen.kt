package com.example.marvelcompose.ui.screens.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.marvelcompose.data.entities.MarvelItem

@Composable
fun <T : MarvelItem> MarvelItemListScreen(
    items: List<T>,
    loading: Boolean,
    onClick: (T) -> Unit
) {
    MarvelItemsList(
        items = items,
        loading = loading,
        onClick = onClick
    )
}

@Composable
fun <T : MarvelItem> MarvelItemsList(
    items: List<T>,
    loading: Boolean,
    onClick: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        if(loading){
            CircularProgressIndicator()
        }
        if(items.isNotEmpty()) {
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
    }
}