package com.example.marvelcompose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.marvelcompose.R
import com.example.marvelcompose.ui.navigation.AppBarIcon
import com.example.marvelcompose.ui.navigation.AppBottomNavigation
import com.example.marvelcompose.ui.navigation.DrawerContent
import com.example.marvelcompose.ui.navigation.Navigation
import com.example.marvelcompose.ui.theme.MarvelComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelApp() {
    val appState = rememberMarvelAppState()

    MarvelScreen {
        ModalNavigationDrawer(
            drawerState = appState.drawerState,
            drawerContent = {
                DrawerContent(
                    drawerOptions = MarvelAppState.DRAWER_OPTIONS,
                    onOptionClick = {
                        appState.onDrawerOptionClick(it)
                    }
                )
            }
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(stringResource(id = R.string.app_name)) },
                        navigationIcon = {
                            if (appState.showUpNavigation) AppBarIcon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                onClick = { appState.onUpClick() }
                            ) else AppBarIcon(
                                imageVector = Icons.Default.Menu,
                                onClick = { appState.onMenuClick() }
                            )
                        }
                    )
                },
                bottomBar = {
                    if (appState.showBottomNavigation) {
                        AppBottomNavigation(
                            bottomNavOptions = MarvelAppState.BOTTOM_NAV_OPTIONS,
                            currentRoute = appState.currentRoute,
                            onNavItemClick = { appState.onNavItemClick(it) }
                        )
                    }
                },

                ) { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    Navigation(appState.navController)
                }
            }
        }
    }
}

@Composable
fun MarvelScreen(content: @Composable () -> Unit) {
    MarvelComposeTheme {
        Surface {
            content()
        }
    }
}