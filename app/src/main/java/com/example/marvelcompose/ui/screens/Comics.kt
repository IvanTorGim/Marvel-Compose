package com.example.marvelcompose.ui.screens

import android.app.ActionBar.Tab
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.marvelcompose.R
import com.example.marvelcompose.data.entities.Comic
import com.example.marvelcompose.data.repositories.ComicsRepository
import com.example.marvelcompose.data.repositories.toStringFormat
import com.example.marvelcompose.ui.screens.common.MarvelItemDetailScreen
import com.example.marvelcompose.ui.screens.common.MarvelItemsList
import kotlinx.coroutines.launch
import kotlin.math.max

@Composable
fun ComicsScreen(onClick: (Comic) -> Unit) {
    var comicsState by remember { mutableStateOf(emptyList<Comic>()) }
    LaunchedEffect(Unit) { comicsState = ComicsRepository.get() }
    val formats = Comic.Format.entries
    val pagerState = rememberPagerState { formats.size }

    Column {
        ComicFormatsTabRow(
            pagerState = pagerState,
            formats = formats
        )
        HorizontalPager(
            state = pagerState
        ) {
            MarvelItemsList(
                items = comicsState,
                onClick = onClick
            )
        }
    }
}

@Composable
fun ComicFormatsTabRow(pagerState: PagerState, formats: List<Comic.Format>) {
    val scope = rememberCoroutineScope()
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        edgePadding = 0.dp
    ) {
        formats.forEach {
            Tab(
                selected = pagerState.currentPage == it.ordinal,
                onClick = { scope.launch { pagerState.animateScrollToPage(it.ordinal) } },
                text = { Text(text = stringResource(id = it.toStringRes()).uppercase()) }
            )
        }
    }
}

@Composable
fun ComicDetailScreen(comicId: Int) {
    var comicState by remember { mutableStateOf<Comic?>(null) }
    LaunchedEffect(Unit) { comicState = ComicsRepository.find(comicId) }
    comicState?.let {
        MarvelItemDetailScreen(
            marvelItem = it
        )
    }
}

@StringRes
private fun Comic.Format.toStringRes(): Int = when (this) {
    Comic.Format.COMIC -> R.string.comic
    Comic.Format.MAGAZINE -> R.string.magazine
    Comic.Format.TRADE_PAPERBACK -> R.string.trade_paperback
    Comic.Format.HARDCOVER -> R.string.hardcover
    Comic.Format.DIGEST -> R.string.digest
    Comic.Format.GRAPHIC_NOVEL -> R.string.graphic_novel
    Comic.Format.DIGITAL_COMIC -> R.string.digital_comic
    Comic.Format.INFINITE_COMIC -> R.string.infinite_comic
}