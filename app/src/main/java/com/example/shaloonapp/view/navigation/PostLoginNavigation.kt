package com.example.shaloonapp.view.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

import androidx.navigation.compose.composable
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes.ALL_APPOINTMENT_SCREEN

import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

import com.example.shaloonapp.view.navigation.PostLoginNavRoutes.HOME_SCREEN
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes.SELECT_BARBER_SCREEN
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes.SELECT_SERVICE_SCREEN
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes.SELECT_TIME_SCREEN
import com.example.shaloonapp.view.screens.HomeScreen
import com.example.shaloonapp.view.screens.post_login.AllAppointmentScreen
import com.example.shaloonapp.view.screens.post_login.SelectBarber
import com.example.shaloonapp.view.screens.post_login.SelectServiceScreen
import com.example.shaloonapp.view.screens.post_login.SelectServiceTimeSlot

import com.example.shaloonapp.viewmodel.PostLoginSharedViewModel

fun NavGraphBuilder.PostLoginNavigation(
    navController: NavHostController,
    postLoginSharedViewModel: PostLoginSharedViewModel
) {

    composable(route = HOME_SCREEN) { HomeScreen(navController,postLoginSharedViewModel) }
    composable(route = SELECT_SERVICE_SCREEN) { SelectServiceScreen(navController,postLoginSharedViewModel) }
    composable(route = SELECT_BARBER_SCREEN) { SelectBarber(navController,postLoginSharedViewModel) }
    composable(route = ALL_APPOINTMENT_SCREEN) { AllAppointmentScreen(navController,postLoginSharedViewModel)}

    composable(
        route = SELECT_TIME_SCREEN,
        arguments = listOf(
            navArgument("serviceTime") {
                type = NavType.IntType
            }
        )
    ) {
        it.arguments?.getInt("serviceTime")?.let {serviceTime ->
            SelectServiceTimeSlot(navController, serviceTime)
        }
    }
}

object PostLoginNavRoutes {
    const val SELECT_SERVICE_SCREEN = "SelectServiceScreen"
    const val SELECT_BARBER_SCREEN = "SelectBarberScreen"
    const val ALL_APPOINTMENT_SCREEN = "AllAppointmentScreen"
    const val SELECT_TIME_SCREEN = "SelectTimeScreen/{serviceTime}"
    const val HOME_SCREEN = "HomeScreen"
}