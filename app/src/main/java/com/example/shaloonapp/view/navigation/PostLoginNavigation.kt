package com.example.shaloonapp.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes.HOME_SCREEN
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes.SELECT_BARBER_SCREEN
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes.SELECT_SERVICE_SCREEN
import com.example.shaloonapp.view.screens.HomeScreen
import com.example.shaloonapp.view.screens.post_login.SelectBarber
import com.example.shaloonapp.view.screens.post_login.SelectServiceScreen

fun NavGraphBuilder.PostLoginNavigation(navController: NavHostController) {
    composable(route = HOME_SCREEN) { HomeScreen(navController) }
    composable(route = SELECT_SERVICE_SCREEN) { SelectServiceScreen(navController) }
    composable(route = SELECT_BARBER_SCREEN) { SelectBarber(navController) }
}

object PostLoginNavRoutes {
      const val SELECT_SERVICE_SCREEN = "SelectServiceScreen"
      const val SELECT_BARBER_SCREEN = "SelectBarberScreen"
      const val HOME_SCREEN = "HomeScreen"

}