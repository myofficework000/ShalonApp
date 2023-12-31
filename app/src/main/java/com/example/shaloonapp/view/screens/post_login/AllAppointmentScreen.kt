package com.example.shaloonapp.view.screens.post_login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shaloonapp.R
import com.example.shaloonapp.model.dto.AppointmentWithListOfService
import com.example.shaloonapp.ui.theme.AllAppointmentScreen_Icon_Canceled
import com.example.shaloonapp.ui.theme.AllAppointmentScreen_Icon_Confirmed
import com.example.shaloonapp.ui.theme.AllAppointmentScreen_TitleScreen_BackGround
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes.APPOINTMENT_DETAIL_SCREEN
import com.example.shaloonapp.viewmodel.AllAppointmentScreenViewModel
import com.example.shaloonapp.viewmodel.PostLoginSharedViewModel


@Preview(showBackground = true)
@Composable
fun PreviewAllApointmentScreen(){
    AllAppointmentScreen(rememberNavController(), viewModel())
}

@Composable
fun AllAppointmentScreen(
    navController: NavHostController,
    postLoginSharedViewModel: PostLoginSharedViewModel
) {
    val allAppointmentScreenViewModel: AllAppointmentScreenViewModel = hiltViewModel()

    val listOfAppointment = allAppointmentScreenViewModel.listOfAppointment.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {


        Box(modifier = Modifier
            .background(AllAppointmentScreen_TitleScreen_BackGround)
            .wrapContentHeight()
            .fillMaxWidth()
            ){
            Text(text = stringResource(R.string.appointment_screen_title),
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.padding(20.dp))
        }
        listOfAppointment.value?.let {
            LazyColumn(modifier = Modifier
                .padding(20.dp)
                .fillMaxHeight()
                .fillMaxWidth()
            ){
                items(it){
                        appointment ->
                    AppointmentViewHolder(
                        appointment = appointment,
                        onAppointmentSelected = {
                            postLoginSharedViewModel.currentAppointment.value =it
                            navController.navigate(APPOINTMENT_DETAIL_SCREEN) },
                    )
                }
            }
        }

    }
}

@Composable
fun AppointmentViewHolder(
    appointment: AppointmentWithListOfService,
    onAppointmentSelected: (AppointmentWithListOfService) -> Unit){

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            ,
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        ConstraintLayout(
            Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            val (  startContainer, moreDetailButton) = createRefs()

            IconButton(
                onClick = {onAppointmentSelected(appointment)},
                modifier = Modifier
                    .padding(10.dp)
                    .constrainAs(moreDetailButton) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.wrapContent
                        height = Dimension.wrapContent
                    }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = ""
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp)
                    .constrainAs(startContainer) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.wrapContent
                        height = Dimension.wrapContent
                    }
            ) {
                Row( modifier = Modifier) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier)
                    {
                        Row( modifier = Modifier.padding(vertical = 5.dp)) {
                            Text(text = "Appointment: #")
                            Text(text = appointment.appointment.appointmentId.toString())
                        }
                        Row( modifier = Modifier.padding(vertical = 5.dp)) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "",
                                modifier = Modifier.padding(horizontal = 5.dp)
                            )
                            Text(text = appointment.appointment.appointmentDate)
                        }
                        Row( modifier = Modifier.padding(vertical = 5.dp)) {
                            Icon(
                                imageVector = ImageVector.vectorResource(
                                    R.drawable.baseline_access_time_filled_24),
                                contentDescription = "",
                                modifier = Modifier.padding(horizontal = 5.dp)
                            )
                            Text(text = appointment.appointment.appointmentTime)
                        }
                        Row( modifier = Modifier.padding(vertical = 5.dp)) {
                            Icon(
                                imageVector =
                                    if(appointment.appointment.status == "Canceled")
                                        ImageVector.vectorResource(
                                            R.drawable.baseline_block_24)
                                    else
                                        ImageVector.vectorResource(
                                            R.drawable.baseline_timelapse_24),
                                tint =
                                    if(appointment.appointment.status == "Canceled")
                                        AllAppointmentScreen_Icon_Canceled
                                    else
                                        AllAppointmentScreen_Icon_Confirmed
                                    ,
                                contentDescription = "",
                                modifier = Modifier.padding(horizontal = 5.dp)
                            )
                            Text(text = appointment.appointment.status)
                        }
                    }
                }
            }
        }
    }
}
