package com.example.shaloonapp.view.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes.HOME_SCREEN
import com.example.shaloonapp.view.navigation.PreLoginNavRoutes.LOGIN_SCREEN
import com.example.shaloonapp.view.navigation.PreLoginNavRoutes.REGISTER_SCREEN
import com.example.shaloonapp.view.navigation.PreLoginNavRoutes.SPLASH_SCREEN
import com.example.shaloonapp.view.screens.LoginScreen
import com.example.shaloonapp.view.screens.RegisterScreen
import com.example.shaloonapp.view.screens.SplashScreen
import com.example.shaloonapp.viewmodel.PostLoginSharedViewModel

@Composable
fun PreLoginNavigation(startDestination: String = SPLASH_SCREEN) {
    val navController = rememberNavController()

    val postLoginSharedViewModel: PostLoginSharedViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        //Add all the destinations
        composable(route = SPLASH_SCREEN) { SplashScreen(navController) }
        composable(route = LOGIN_SCREEN) { LoginScreen(navController) }
        composable(route = REGISTER_SCREEN) { RegisterScreen(navController) }
        PostLoginNavigation(navController,postLoginSharedViewModel)
    }
}
object PreLoginNavRoutes {
    //  const val SELECT_SERVICE_SCREEN = "SelectServiceScreen"
    const val SPLASH_SCREEN = "SplashScreen"
    const val LOGIN_SCREEN = "LoginScreen"
    const val REGISTER_SCREEN = "RegisterScreen"
}