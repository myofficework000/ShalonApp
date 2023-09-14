package com.example.shaloonapp.view.screens

import android.annotation.SuppressLint
import android.support.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.shaloonapp.R
import com.example.shaloonapp.model.dto.Barber
import com.example.shaloonapp.model.dto.Service
import com.example.shaloonapp.model.utils.deleteSharedPreferences
import com.example.shaloonapp.ui.theme.Purple40
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes.ALL_APPOINTMENT_SCREEN
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes.HOME_SCREEN
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes.SELECT_BARBER_SCREEN
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes.STORE_DETAILS_SCREEN
import com.example.shaloonapp.view.navigation.PreLoginNavRoutes.LOGIN_SCREEN
import com.example.shaloonapp.view.screens.components.RoundedCard
import com.example.shaloonapp.view.util.getImgURLFromFirebase
import com.example.shaloonapp.viewmodel.HomeScreenViewModel
import com.example.shaloonapp.viewmodel.PostLoginSharedViewModel
import com.example.shaloonapp.viewmodel.SelectServiceScreenViewModel
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, postLoginSharedViewModel: PostLoginSharedViewModel){
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Purple40),
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "My Salon",
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Purple40)
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 10.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
                text = "Hello Pavani"
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 10.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                style = MaterialTheme.typography.bodySmall,
                text = "Let's make your hair more attractive"
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                ListItem(
                    painterResource(id = R.drawable.baseline_calendar_month_24),
                    text = "Schedule"
                ){
                    navController.navigate(SELECT_BARBER_SCREEN)
                }
                ListItem(
                    painterResource(id = R.drawable.baseline_history_24),
                    text = "History"
                ){
                    navController.navigate(PostLoginNavRoutes.ALL_APPOINTMENT_SCREEN)
                }
                ListItem(
                    painterResource(id = R.drawable.baseline_access_time_24),
                    text = "About"
                ){
                    navController.navigate(STORE_DETAILS_SCREEN)
                }
                ListItem(
                    painterResource(id = R.drawable.baseline_logout_24),
                    text = "Logout"
                ){
                    context.deleteSharedPreferences()
                    navController.navigate(LOGIN_SCREEN){
                        popUpTo(HOME_SCREEN){
                            inclusive = true
                        }
                    }
                }

            }

            CardItems(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 10.dp)
            )


        }
    }


}

@Composable
fun CardItems(modifier: Modifier){


    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
    val listOfBarber = homeScreenViewModel.listOfBarber.collectAsState()

    val selectServiceScreenViewModel: SelectServiceScreenViewModel = hiltViewModel()
    val listOfService = selectServiceScreenViewModel.listOfService.collectAsState()

    RoundedCard(modifier = modifier.fillMaxWidth()){
        Column(modifier = Modifier.padding(top = 24.dp)) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 10.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.labelLarge,
                text = "Available Services"
            )

            LazyRow(modifier = Modifier.padding(start = 5.dp,top=10.dp, end = 5.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)){
                items(listOfService.value ?:ArrayList()){
                    ServiceItem(modifier = Modifier
                        .wrapContentHeight()
                        .wrapContentWidth(),service =it)
                }
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.labelLarge,
                text = "Available Barbers"
            )

            LazyRow(modifier = Modifier.padding(start = 5.dp,top=10.dp, end = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)){
                items(listOfBarber.value ?:ArrayList()){
                    BarberItem(modifier = Modifier
                        .wrapContentHeight()
                        .wrapContentWidth(),barber =it)
                }
            }
        }

    }
}

@Composable
fun BarberItem(modifier: Modifier,barber: Barber){

//    Card(colors = CardDefaults.cardColors(containerColor = Color.White))
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

        Column(modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally) {

            AsyncImage(
                model = imgURL,
                contentDescription = "service img",
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_launcher_background),
                error= painterResource(R.drawable.no_img_error),
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 10.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.labelLarge,
                text = barber.firstName
            )
            RatingBar(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 2.dp),
                value = barber.rating.toFloat(),
                size = 10.dp,
                spaceBetween= 1.dp,
                style = RatingBarStyle.Default,
                onValueChange = {},
                onRatingChanged = {}
            )
        }


}

@Composable
fun ServiceItem(modifier: Modifier,service: Service){

//    Card(colors = CardDefaults.cardColors(containerColor = Color.White))
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
        Column(modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally) {

            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillBounds,
                model = imgURL,
                contentDescription = "service img",
                placeholder = painterResource(R.drawable.ic_launcher_background),
                error= painterResource(R.drawable.ic_launcher_background)
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 10.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.labelLarge,
                text = service.name
            )
        }


}




@Composable
fun ListItem(@SuppressLint("SupportAnnotationUsage") @DrawableRes image: Painter,
             text:String,
             onClick: () -> Unit = {}
){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(modifier = Modifier.clickable { onClick() },
            colors = CardDefaults.cardColors(containerColor = Color.White)) {
            Image(
                modifier = Modifier
                    .size(70.dp)
                    .padding(16.dp),
                contentScale = ContentScale.FillBounds,
                painter = image, contentDescription = "image"
            )
        }
        Text(
            modifier = Modifier,
            color = Color.White,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.labelLarge,
            text = text
        )
    }

}


