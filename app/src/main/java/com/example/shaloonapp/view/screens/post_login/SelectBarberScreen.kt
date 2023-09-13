package com.example.shaloonapp.view.screens.post_login

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.shaloonapp.R
import com.example.shaloonapp.model.dto.Barber
import com.example.shaloonapp.ui.theme.SelectServiceScreen_TitleScreen_BackGround
import com.example.shaloonapp.ui.theme.Yellow
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes.SELECT_SERVICE_SCREEN
import com.example.shaloonapp.view.util.getImgURLFromFirebase
import com.example.shaloonapp.viewmodel.HomeScreenViewModel
import com.example.shaloonapp.viewmodel.PostLoginSharedViewModel
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectBarber(navController: NavController, postLoginSharedViewModel: PostLoginSharedViewModel){
    val context = LocalContext.current
    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
    val listOfBarber = homeScreenViewModel.listOfBarber.collectAsState()
    var selectedBarber by remember { mutableStateOf<Barber?>(null) }

    postLoginSharedViewModel.currentBarber.value = selectedBarber

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                color = Color.White,
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                text = "Available Barbars"
            )
        },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = SelectServiceScreen_TitleScreen_BackGround)
            )

        LazyColumn(
            modifier = Modifier
                .padding(start = 8.dp, top = 10.dp, end = 8.dp)
                .weight(0.8F),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(listOfBarber.value ?: ArrayList()) {
                BarbarItem(
                    modifier = Modifier
                        .wrapContentHeight()
                        .wrapContentWidth(),
                    barber = it,
                    selectedBarber = selectedBarber,
                    onBarberSelected = { barber ->
                        selectedBarber = barber
                    }
                )
            }
        }

        Button(
            onClick = {
                selectedBarber?.let {
                    navController.navigate(SELECT_SERVICE_SCREEN)
                  //  Log.i("SelectBarber",  postLoginSharedViewModel.currentBarber.value.toString())
                } ?: Toast.makeText(context,"Please Select Barber", Toast.LENGTH_SHORT).show()
            },
            shape= RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(Yellow),
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .align(Alignment.End)
        ) {
            Text(
                text = "next",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
            )
        }
    }

}


@Composable
fun BarbarItem(modifier: Modifier,
               barber: Barber,
               selectedBarber: Barber?,
               onBarberSelected:(Barber) -> Unit
){
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

    Card(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .selectable(
            selected = selectedBarber == barber,
            onClick = { onBarberSelected(barber) }
        ),
        border = BorderStroke(1.dp, Color.LightGray),
        shape = RoundedCornerShape(8.dp),
        colors = if(selectedBarber == barber)
            CardDefaults.cardColors(containerColor = Color.LightGray)
        else
            CardDefaults.cardColors(containerColor = Color.White)
    ){
        Column(modifier = Modifier.padding(8.dp)) {
            Row {
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

                Column {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, start = 10.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium,
                        text = barber.firstName
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, start = 10.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelLarge,
                        text = barber.experience
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

        }
    }

}