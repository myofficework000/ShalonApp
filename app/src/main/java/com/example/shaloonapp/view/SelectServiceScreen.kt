package com.example.shaloonapp.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shaloonapp.R
import com.example.shaloonapp.model.dto.Service
import com.example.shaloonapp.ui.theme.Purple40
import com.example.shaloonapp.ui.theme.SelectServiceScreen_TitleScreen_BackGround
import com.example.shaloonapp.viewmodel.SelectServiceScreenViewModel

@Preview(showBackground = true)
@Composable
fun PreviewSelectServiceScreen(){
    SelectServiceScreen(rememberNavController())
}

@Composable
fun SelectServiceScreen(navController: NavHostController) {

    val selectServiceScreenViewModel: SelectServiceScreenViewModel = hiltViewModel()

    val listOfService = selectServiceScreenViewModel.listOfService.observeAsState()

    var selectedService by remember { mutableStateOf<Service?>(null) }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (screenTitle, title, serviceContainer, btnChooseDate, btnAnotherBarber) = createRefs()

        Box(modifier = Modifier
            .background(SelectServiceScreen_TitleScreen_BackGround)
            .constrainAs(screenTitle) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.wrapContent
            }){
            Text(text = "Select Service",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.padding(20.dp))
        }

        Text(
            text = "Haircut",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .constrainAs(title) {
                    top.linkTo(screenTitle.bottom)
                    start.linkTo(parent.start)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                }
        )
        LazyColumn(modifier = Modifier
            .padding(20.dp)
            .constrainAs(serviceContainer) {
                bottom.linkTo(btnChooseDate.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(title.bottom)

                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }){
           items(listOfService.value ?:ArrayList<Service>()){
               service ->
               ServiceViewHolder(
                   service = service,
                   selectedService = selectedService,
                   onServiceSelected = {selectedService = it}
               )
           }
        }

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(Purple40),
            modifier = Modifier
                .padding(20.dp)
                .constrainAs(btnChooseDate) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                }
        ) {
            Text(text = "Choose Date")
        }
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(Purple40),
            modifier = Modifier
                .padding(20.dp)
                .constrainAs(btnAnotherBarber) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                }
        ) {
            Text(text = "Choose Date")
        }

    }
}

@Composable
fun ServiceViewHolder(
    service: Service,
    selectedService: Service?,
    onServiceSelected: (Service) -> Unit){

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .selectable(
                selected = (selectedService == service),
                onClick = { onServiceSelected(service) }
            ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = if (selectedService == service) CardDefaults.cardColors(Color.Gray)
                            else CardDefaults.cardColors(Color.White),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        ConstraintLayout(
            Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            val (img, serviceTitle, serviceDuration, servicePrice, btnDetail, bottomContainer) = createRefs()
            Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "hearFrom Img ",
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .padding( 15.dp)
                    .constrainAs(img) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    })


            Text(text = service.title,
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
                OutlinedButton(
                    onClick = { /* Action to perform */ },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                    border = null,
                    modifier = Modifier

                ) {
                    Text(text = "More Detail")
                }
            }


        }
    }
}