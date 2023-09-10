package com.example.shaloonapp.view.screens

import android.annotation.SuppressLint
import android.support.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shaloonapp.R
import com.example.shaloonapp.model.dto.Barber
import com.example.shaloonapp.model.dto.Service
import com.example.shaloonapp.ui.theme.Purple40
import com.example.shaloonapp.view.screens.components.RoundedCard
import com.example.shaloonapp.viewmodel.HomeScreenViewModel
import com.example.shaloonapp.viewmodel.SelectServiceScreenViewModel
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomeScreen(){

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
                )
                ListItem(
                    painterResource(id = R.drawable.baseline_history_24),
                    text = "History"
                )
                ListItem(
                    painterResource(id = R.drawable.baseline_access_time_24),
                    text = "Hours"
                )
                ListItem(
                    painterResource(id = R.drawable.baseline_logout_24),
                    text = "Logout"
                )

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
    val listOfBarber = homeScreenViewModel.listOfBarber.observeAsState()

    val selectServiceScreenViewModel: SelectServiceScreenViewModel = hiltViewModel()
    val listOfService = selectServiceScreenViewModel.listOfService.observeAsState()

    RoundedCard(modifier = modifier.fillMaxWidth()){
        Column(modifier = Modifier.padding(top = 24.dp)) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 10.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.labelLarge,
                text = "Available HairCuts"
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
                text = "Available Barbars"
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

        Column(modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(100.dp),
                contentScale = ContentScale.FillBounds,
                painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "image"
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
                value = barber.rating,
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

        Column(modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillBounds,
                painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "image"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 10.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.labelLarge,
                text = service.title
            )
        }


}




@Composable
fun ListItem(@SuppressLint("SupportAnnotationUsage") @DrawableRes image: Painter,text:String){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(modifier = Modifier,
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


