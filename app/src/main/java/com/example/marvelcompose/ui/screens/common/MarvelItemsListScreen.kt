package com.example.marvelcompose.ui.screens.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.marvelcompose.R
import com.example.marvelcompose.data.entities.MarvelItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : MarvelItem> MarvelItemListScreen(items: List<T>, onClick: (T) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(180.dp),
            contentPadding = PaddingValues(4.dp),
            modifier = Modifier.padding(padding)
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