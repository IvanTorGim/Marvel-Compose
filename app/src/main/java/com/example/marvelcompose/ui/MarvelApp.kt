package com.example.marvelcompose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.marvelcompose.R
import com.example.marvelcompose.ui.navigation.AppBarIcon
import com.example.marvelcompose.ui.navigation.AppBottomNavigation
import com.example.marvelcompose.ui.navigation.DrawerContent
import com.example.marvelcompose.ui.navigation.NavItem
import com.example.marvelcompose.ui.navigation.Navigation
import com.example.marvelcompose.ui.navigation.navigatePoppingUpToStartDestination
import com.example.marvelcompose.ui.theme.MarvelComposeTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""
    val showUpNavigation = currentRoute !in NavItem.entries.map { it.navCommand.route }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val drawerOptions = listOf(NavItem.HOME, NavItem.SETTINGS)
    val bottomNavOptions = listOf(NavItem.CHARACTERS, NavItem.COMICS, NavItem.EVENTS)

    MarvelScreen {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                DrawerContent(
                    drawerOptions = drawerOptions,
                    onOptionClick = {navItem ->
                        scope.launch { drawerState.close() }
                        navController.navigate(navItem.navCommand.route)
                    }
                )
            }
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(stringResource(id = R.string.app_name)) },
                        navigationIcon = {
                            if (showUpNavigation) AppBarIcon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                onClick = { navController.popBackStack() }
                            ) else AppBarIcon(
                                imageVector = Icons.Default.Menu,
                                onClick = { scope.launch { drawerState.open() } }
                            )
                        }
                    )
                },
                bottomBar = {
                    AppBottomNavigation(
                        bottomNavOptions = bottomNavOptions,
                        currentRoute = currentRoute,
                        onNavItemClick = { navController.navigatePoppingUpToStartDestination(it.navCommand.route) }
                    )
                },

                ) { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    Navigation(navController)
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