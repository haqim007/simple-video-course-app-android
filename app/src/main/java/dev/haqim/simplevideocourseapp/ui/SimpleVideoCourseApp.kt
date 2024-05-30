package dev.haqim.simplevideocourseapp.ui

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.haqim.simplevideocourseapp.ui.detail.DetailScreen
import dev.haqim.simplevideocourseapp.ui.home.HomeScreen

@Composable
fun SimpleVideoCourseApp() {
    val navController = rememberNavController()
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        SimpleVideoCourseNavHost(navController)
    }
}

@Composable
fun SimpleVideoCourseNavHost(
    navController: NavHostController
){
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route){
            HomeScreen(
                onClick = {title ->
                    navController.navigate(
                        Screen.Detail.createRoute(title = title)
                    )
                }
            )
        }

        composable(route = Screen.Detail.route, arguments = Screen.Detail.navArgs){
            val title =  it.arguments?.getString("title")
            DetailScreen(
                title = title!!,
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}

