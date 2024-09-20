package com.example.marvelcompose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.marvelcompose.ui.navigation.AppBottomNavigation
import com.example.marvelcompose.ui.navigation.Navigation
import com.example.marvelcompose.ui.navigation.navigatePoppingUpToStartDestination
import com.example.marvelcompose.ui.theme.MarvelComposeTheme

@Composable
fun MarvelApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""

    MarvelScreen {
        Scaffold(
            bottomBar = {
                AppBottomNavigation(
                    currentRoute = currentRoute,
                    onNavItemClick = { navController.navigatePoppingUpToStartDestination(it.navCommand.route) }
                )
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController)
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