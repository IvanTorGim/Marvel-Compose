package com.example.marvelcompose.ui.screens.common

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ShareCompat
import com.example.marvelcompose.R
import com.example.marvelcompose.data.entities.MarvelItem
import com.example.marvelcompose.data.entities.Url
import com.example.marvelcompose.ui.navigation.AppBarIcon
import com.example.marvelcompose.ui.navigation.ArrowBackIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelItemDetailScaffold(
    marvelItem: MarvelItem,
    onUpClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = marvelItem.title) },
                navigationIcon = { ArrowBackIcon(onUpClick) },
                actions = { AppBarOverflowMenu(marvelItem.urls) }
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    AppBarIcon(Icons.Default.Menu, {})
                    AppBarIcon(Icons.Default.Favorite, {})
                },
                floatingActionButton = {
                    if (marvelItem.urls.isNotEmpty()) {
                        FloatingActionButton(onClick = {
                            shareCharacter(
                                context,
                                marvelItem.title,
                                marvelItem.urls.first()
                            )
                        }) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = stringResource(id = R.string.share_character)
                            )
                        }
                    }
                }
            )
        },
        content = content
    )
}

private fun shareCharacter(context: Context, name: String, url: Url) {
    ShareCompat
        .IntentBuilder(context)
        .setType("text/plain")
        .setSubject(name)
        .setText(url.destination)
        .intent
        .also(context::startActivity)
}
