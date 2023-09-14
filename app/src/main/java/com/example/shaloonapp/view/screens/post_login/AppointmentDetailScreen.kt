@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.shaloonapp.view.screens.post_login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.shaloonapp.R
import com.example.shaloonapp.model.dto.Appointment
import com.example.shaloonapp.model.dto.Barber
import com.example.shaloonapp.model.dto.Service
import com.example.shaloonapp.ui.theme.AllAppointmentScreen_Icon_Canceled
import com.example.shaloonapp.ui.theme.AllAppointmentScreen_Icon_Confirmed
import com.example.shaloonapp.ui.theme.AppointmentDetailScreen_TitleScreen_BackGround
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes.HOME_SCREEN
import com.example.shaloonapp.view.util.getImgURLFromFirebase
import com.example.shaloonapp.viewmodel.PostLoginSharedViewModel
import kotlinx.coroutines.launch


@Composable
fun AppointmentDetailScreen(
    navController: NavHostController,
    postLoginSharedViewModel: PostLoginSharedViewModel
){

    val currentAppointment = postLoginSharedViewModel.currentAppointment.observeAsState()


    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        AppointmentDetailScreenHeader(navController = navController)
        currentAppointment.value?.let {
            AppointmentDetailContainer(appointment = it.appointment)
            BarberDetailsContainer(barber = it.barber)
            ServicesContainer(services = it.listOfServices)
        }

        Button(
            onClick = { navController.navigate(HOME_SCREEN) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)) {
            Text(text = "Home")
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentDetailScreenHeader(navController: NavHostController,){
    TopAppBar(
        modifier = Modifier .fillMaxWidth(),
        title = {
            Text(text = "Appointment Details",
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
fun AppointmentDetailContainer(appointment: Appointment) {
    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Row( modifier = Modifier) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier)
            {
                Row( modifier = Modifier.padding( 10.dp)) {
                    Text(text = stringResource(R.string.appointment_number),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,)
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(text = appointment.appointmentId.toString(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,)
                }

                Row(
                    modifier = Modifier.padding(10.dp).fillMaxWidth(),
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


                Row( modifier = Modifier.padding(10.dp)) {
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

@Composable
fun BarberDetailsContainer( barber: Barber){
    var imgURL : String by remember { mutableStateOf("") }

    // Create a CoroutineScope that follows this composable's lifecycle
    val composableScope = rememberCoroutineScope()
    LaunchedEffect(Unit){
        composableScope.launch {
            imgURL = try {
                getImgURLFromFirebase(barber.imgUrL)
            }catch (e: Exception){
                ""
            }

        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(Modifier
            .padding(horizontal = 20.dp) ) {

            Text(text = "Barber",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,)

            Spacer(modifier = Modifier.size(10.dp))

            Text(text = "${barber.firstName} ${barber.lastName}",
                fontSize = 18.sp,)
        }
        AsyncImage(
            model = imgURL,
            contentDescription = "service img",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.ic_launcher_background),
            error= painterResource(R.drawable.no_img_error),
            modifier = Modifier
                .size(100.dp)
        )
    }
}

@Composable
fun ServicesContainer( services: List<Service>){
    val totalCost = services.sumOf { it.price }
    Column(
        modifier = Modifier
            .fillMaxWidth().padding(20.dp)
    ) {
        Text(text = "Services",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,)
        LazyColumn(modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
        ){
            items(services){
                    service ->
                ServiceViewHolder(service = service)
            }
        }
        Text(
            text = stringResource(R.string.total_cost) + "$ $totalCost",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun ServiceViewHolder( service: Service, ){

    var imgURL : String by remember { mutableStateOf("") }

    // Create a CoroutineScope that follows this composable's lifecycle
    val composableScope = rememberCoroutineScope()
    LaunchedEffect(Unit){
        composableScope.launch {
            imgURL = try {
                getImgURLFromFirebase(service.imgURL)
            }catch (e: Exception){
                ""
            }

        }
    }

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            ,
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(Color.White),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        ConstraintLayout(
            Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            val (img, serviceTitle, bottomContainer) = createRefs()

            AsyncImage(
                model = imgURL,
                contentDescription = "service img",
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_launcher_background),
                error= painterResource(R.drawable.no_img_error),
                modifier = Modifier
                    .size(100.dp)
                    .padding(15.dp)
                    .constrainAs(img) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
            )

            Text(text = service.name,
                modifier = Modifier
                    .padding(15.dp)
                    .constrainAs(serviceTitle) {
                        top.linkTo(parent.top)
                        start.linkTo(img.end)
                    })
            Row(
                modifier = Modifier.constrainAs(bottomContainer){
                    top.linkTo(serviceTitle.bottom)
                    start.linkTo(img.end)
                    end.linkTo(parent.end)
                }
            ) {
                Text(text = "${service.durationInMinute} min",
                    modifier = Modifier
                        .padding( 15.dp)

                )
                Text(text = " $ ${service.price}",
                    modifier = Modifier
                        .padding(15.dp)

                )
            }
        }
    }
}
