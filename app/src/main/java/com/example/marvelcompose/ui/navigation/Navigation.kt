package com.example.marvelcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.marvelcompose.ui.screens.CharacterDetailScreen
import com.example.marvelcompose.ui.screens.CharacterScreen
import com.example.marvelcompose.ui.screens.ComicDetailScreen
import com.example.marvelcompose.ui.screens.ComicsScreen
import com.example.marvelcompose.ui.screens.EventDetailScreen
import com.example.marvelcompose.ui.screens.EventsScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Feature.CHARACTER.route
    ) {
        charactersNav(navController)
        comicsNav(navController)
        eventsNav(navController)
    }
}

private fun NavGraphBuilder.charactersNav(navController: NavController) {
    navigation(
        startDestination = NavItem.ContentType(Feature.CHARACTER).route,
        route = Feature.CHARACTER.route
    ) {
        composable(NavItem.ContentType(Feature.CHARACTER)) {
            CharacterScreen { character ->
                navController.navigate(
                    NavItem.ContentDetail(Feature.CHARACTER).createRoute(character.id)
                )
            }
        }
        composable(NavItem.ContentDetail(Feature.CHARACTER)) { navBackStackEntry ->
            CharacterDetailScreen(
                id = navBackStackEntry.findArg<Int>(NavArg.ItemId),
                onUpClick = { navController.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.comicsNav(navController: NavController) {
    navigation(
        startDestination = NavItem.ContentType(Feature.COMICS).route,
        route = Feature.COMICS.route
    ) {
        composable(NavItem.ContentType(Feature.COMICS)) {
            ComicsScreen { comic ->
                navController.navigate(
                    NavItem.ContentDetail(Feature.COMICS).createRoute(comic.id)
                )
            }
        }
        composable(NavItem.ContentDetail(Feature.COMICS)) { navBackStackEntry ->
            ComicDetailScreen(
                comicId = navBackStackEntry.findArg<Int>(NavArg.ItemId),
                onUpClick = { navController.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.eventsNav(navController: NavController) {
    navigation(
        startDestination = NavItem.ContentType(Feature.EVENTS).route,
        route = Feature.EVENTS.route
    ) {
        composable(NavItem.ContentType(Feature.EVENTS)) {
            EventsScreen { event ->
                navController.navigate(
                    NavItem.ContentDetail(Feature.EVENTS).createRoute(event.id)
                )
            }
        }
        composable(NavItem.ContentDetail(Feature.EVENTS)) { navBackStackEntry ->
            EventDetailScreen(
                eventId = navBackStackEntry.findArg<Int>(NavArg.ItemId),
                onUpClick = { navController.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}