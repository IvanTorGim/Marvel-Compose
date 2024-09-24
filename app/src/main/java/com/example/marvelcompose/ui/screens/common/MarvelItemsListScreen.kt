package com.example.marvelcompose.ui.screens.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.marvelcompose.data.entities.MarvelItem
import com.example.marvelcompose.data.entities.Result
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : MarvelItem> MarvelItemListScreen(
    marvelItems: Result<List<T>>,
    loading: Boolean,
    onClick: (T) -> Unit
) {

    var showBottomSheet by remember { mutableStateOf(false) }
    var bottomSheetItem by remember { mutableStateOf<T?>(null) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    marvelItems.fold({ ErrorMessage(it) }) { items ->
        MarvelItemsList(
            items = items,
            loading = loading,
            onClick = onClick,
            onItemMore = { item ->
                showBottomSheet = true
                bottomSheetItem = item
            }
        )
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false }
            ) {
                MarvelItemBottomPreview(
                    item = bottomSheetItem,
                    onGoToDetail = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) showBottomSheet = false
                            }
                        onClick(it)
                    }
                )
            }
        }

    }
}

@Composable
fun <T : MarvelItem> MarvelItemsList(
    items: List<T>,
    loading: Boolean,
    onClick: (T) -> Unit,
    onItemMore: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (loading) {
            CircularProgressIndicator()
        }
        if (items.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(180.dp),
                contentPadding = PaddingValues(4.dp),
                modifier = modifier
            ) {
                items(items) {
                    MarvelListItem(
                        marvelItem = it,
                        onItemMore = onItemMore,
                        modifier = Modifier.clickable { onClick(it) }
                    )
                }
            }
        }
    }
}