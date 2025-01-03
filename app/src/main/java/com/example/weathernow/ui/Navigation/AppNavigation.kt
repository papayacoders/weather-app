package com.example.weathernow.ui.Navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weathernow.ui.Screens.WeatherScreen


@Composable
fun AppNavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.HOME_SCREEN) {
        composable(Routes.HOME_SCREEN) {
//inside this we will define the home screen but for that we will need home screen which we will generate4
            //We will have a package inside ui named screen and make all the screens there
            WeatherScreen()
            //if someone  trie to come to nav host and tries to navigate to Routes.HOME_SCREEN then they will be shown this home screen composable
        }

    }
}