package dev.haqim.simplevideocourseapp.ui

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val navArgs: List<NamedNavArgument> = emptyList()
) {
    data object Home: Screen(route = "home")
    data object Detail: Screen(
        route = "course/{title}",
        navArgs = listOf(navArgument("title"){
            type = NavType.StringType
        })
    ){
        fun createRoute(title: String) = "course/$title"
    }
}