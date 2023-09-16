package com.example.shaloonapp.view.screens.post_login

import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shaloonapp.R
import com.example.shaloonapp.model.dto.Appointment
import com.example.shaloonapp.ui.theme.AppointmentDetailScreen_TitleScreen_BackGround
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes.APPOINTMENT_DETAIL_SCREEN
import com.example.shaloonapp.viewmodel.PostLoginSharedViewModel

@Composable
fun AppointmentReviewScreen(
    navController: NavHostController,
    postLoginSharedViewModel: PostLoginSharedViewModel
) {
    val context = LocalContext.current
   // val currentAppointment = postLoginSharedViewModel.currentAppointment.observeAsState()
    val currentBarber = postLoginSharedViewModel.currentBarber.observeAsState()
    val currentService = postLoginSharedViewModel.currentService.observeAsState()
    val currentDate = postLoginSharedViewModel.currentDate.observeAsState()
    val currentTime = postLoginSharedViewModel.currentTime.observeAsState()

    val appointment =
        currentDate.value?.let {
            Appointment(1,currentBarber.value?.barberId,
                it,currentTime.value ?:"",
                currentService.value?.price?.toDouble() ?: 0.0,
                "Confirmed",
                "")
        }

    val insertResult =postLoginSharedViewModel.appointmentInsertResultLiveData.observeAsState()
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        AppointmentReviewScreenHeader(navController = navController)

        currentDate.value?.let {
            AppointmentReviewContainer(date = it, time = currentTime.value ?:"")
        }

        currentBarber.value?.let {
            BarberDetailsContainer(barber = it)
        }
        currentService.value?.let {
            ServicesContainer(services = listOf(it))
        }

        Button(
            onClick = {
                postLoginSharedViewModel.insertAppointment(appointment!!, currentService.value!!)

                insertResult.value?.let {
                        if(it > 0L )
                            navController.navigate(APPOINTMENT_DETAIL_SCREEN)

                    }
                 },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)) {
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
fun AppointmentReviewContainer(date: String, time: String) {
    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {
        Row( modifier = Modifier) {
            Column(
                modifier = Modifier
            )
            {

                Text(text = "Date and Time",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,)
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),

                ) {
                    Row( modifier = Modifier.padding(top=10.dp)) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "",
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                        Text(text = date)
                    }
                    Spacer(modifier = Modifier.size(10.dp))

                    Row( modifier = Modifier.padding(top=10.dp)) {
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                R.drawable.baseline_access_time_filled_24),
                            contentDescription = "",
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                        Text(text = time)
                    }
                }

            }
        }
    }

}

fun showToast(context: Context, message: String){
    Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
}






