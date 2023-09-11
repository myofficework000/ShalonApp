package com.example.shaloonapp.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes.HOME_SCREEN
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes.SELECT_SERVICE_SCREEN
import com.example.shaloonapp.view.screens.HomeScreen
import com.example.shaloonapp.view.screens.post_login.SelectServiceScreen

@Composable
fun PostLoginNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HOME_SCREEN
    ) {
        //Add all the destinations
        composable(route = HOME_SCREEN) { HomeScreen(navController) }
        composable(route = SELECT_SERVICE_SCREEN) { SelectServiceScreen(navController) }

    }
}

object PostLoginNavRoutes {
      const val SELECT_SERVICE_SCREEN = "SelectServiceScreen"
      const val HOME_SCREEN = "HomeScreen"

}