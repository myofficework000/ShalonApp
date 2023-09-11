package com.example.shaloonapp.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shaloonapp.view.navigation.PreLoginNavRoutes.LOGIN_SCREEN
import com.example.shaloonapp.view.navigation.PreLoginNavRoutes.REGISTER_SCREEN
import com.example.shaloonapp.view.navigation.PreLoginNavRoutes.SPLASH_SCREEN
import com.example.shaloonapp.view.screens.LoginScreen
import com.example.shaloonapp.view.screens.RegisterScreen
import com.example.shaloonapp.view.screens.SplashScreen

@Composable
fun PreLoginNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        //Add all the destinations
        composable(route = SPLASH_SCREEN) { SplashScreen(navController) }
        composable(route = LOGIN_SCREEN) { LoginScreen(navController) }
        composable(route = REGISTER_SCREEN) { RegisterScreen(navController) }
        PostLoginNavigation(navController)
    }
}
object PreLoginNavRoutes {
    //  const val SELECT_SERVICE_SCREEN = "SelectServiceScreen"
    const val SPLASH_SCREEN = "SplashScreen"
    const val LOGIN_SCREEN = "LoginScreen"
    const val REGISTER_SCREEN = "RegisterScreen"
}