package com.example.marvelcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.marvelcompose.ui.navigation.Feature.CHARACTER
import com.example.marvelcompose.ui.screens.characterdetail.MarvelItemDetailScreen
import com.example.marvelcompose.ui.screens.characters.CharacterScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CHARACTER.route
    ) {
        charactersNav(navController)
    }
}

private fun NavGraphBuilder.charactersNav(navController: NavController) {
    navigation(
        startDestination = NavItem.ContentType(CHARACTER).route,
        route = CHARACTER.route
    ) {
        composable(NavItem.ContentType(CHARACTER)) {
            CharacterScreen(
                onClick = { character ->
                    navController.navigate(
                        NavItem.ContentDetail(CHARACTER).createRoute(character.id)
                    )
                }
            )
        }
        composable(NavItem.ContentDetail(CHARACTER)) {
            MarvelItemDetailScreen(
                id = it.findArg<Int>(NavArg.ItemId),
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