package com.example.shaloonapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.shaloonapp.ui.theme.ShaloonAppTheme
import com.example.shaloonapp.view.screens.post_login.AllAppointmentScreen
import com.example.shaloonapp.viewmodel.InitializeDataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShaloonAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //PreLoginNavigation()
                    AllAppointmentScreen(navController = rememberNavController())
                    // Pleas use this view model to init data
                    var initializeDataViewModel: InitializeDataViewModel = hiltViewModel()
                    initializeDataViewModel.initDB()
                }
            }
        }
    }
}

