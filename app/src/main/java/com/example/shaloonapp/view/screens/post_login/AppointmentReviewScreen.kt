package com.example.shaloonapp.view.screens.post_login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shaloonapp.R
import com.example.shaloonapp.model.dto.Appointment
import com.example.shaloonapp.ui.theme.AllAppointmentScreen_Icon_Canceled
import com.example.shaloonapp.ui.theme.AllAppointmentScreen_Icon_Confirmed
import com.example.shaloonapp.ui.theme.AppointmentDetailScreen_TitleScreen_BackGround
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes
import com.example.shaloonapp.viewmodel.PostLoginSharedViewModel

@Composable
fun AppointmentReviewScreen(
    navController: NavHostController,
    postLoginSharedViewModel: PostLoginSharedViewModel
) {
    val currentAppointment = postLoginSharedViewModel.currentAppointment.observeAsState()
    val currentBarber = postLoginSharedViewModel.currentBarber.observeAsState()
    val currentService = postLoginSharedViewModel.currentService.observeAsState()


    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        AppointmentReviewScreenHeader(navController = navController)

        currentAppointment.value?.let {
            AppointmentDetailContainer(appointment = it.appointment)
        }
        currentBarber.value?.let {
            BarberDetailsContainer(barber = it)
        }
        currentService.value?.let {
            ServicesContainer(services = listOf(it))
        }

        Button(
            onClick = { navController.navigate(PostLoginNavRoutes.HOME_SCREEN) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)) {
            Text(text = stringResource(R.string.confirm_appointment_btn_txt))
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentReviewScreenHeader(navController: NavHostController,){
    TopAppBar(
        modifier = Modifier .fillMaxWidth(),
        title = {
            Text(text = stringResource(R.string.appointment_review_screen_title),
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.padding(20.dp))
        },
        navigationIcon = {
            IconButton(
                onClick = {navController.popBackStack()},
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    tint = Color.White,
                    contentDescription = ""
                )
            }
        },
        colors = TopAppBarDefaults
            .topAppBarColors(containerColor = AppointmentDetailScreen_TitleScreen_BackGround)
    )
}

@Composable
fun AppointmentReviewContainer(appointment: Appointment) {
    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Row( modifier = Modifier) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
            )
            {

                Text(text = "Date",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,)
                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround

                ) {
                    Row( modifier = Modifier) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "",
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                        Text(text = appointment.appointmentDate)
                    }
                    Spacer(modifier = Modifier.size(10.dp))

                    Row( modifier = Modifier) {
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                R.drawable.baseline_access_time_filled_24),
                            contentDescription = "",
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                        Text(text = appointment.appointmentTime)
                    }
                }


                Row( modifier = Modifier.padding(vertical = 5.dp)) {
                    Icon(
                        imageVector =
                        if(appointment.status == "Canceled")
                            ImageVector.vectorResource(
                                R.drawable.baseline_block_24)
                        else
                            ImageVector.vectorResource(
                                R.drawable.baseline_timelapse_24),
                        tint =
                        if(appointment.status == "Canceled")
                            AllAppointmentScreen_Icon_Canceled
                        else
                            AllAppointmentScreen_Icon_Confirmed
                        ,
                        contentDescription = "",
                        modifier = Modifier.padding(horizontal = 5.dp)
                    )
                    Text(text = appointment.status)
                }
            }
        }
    }

}




