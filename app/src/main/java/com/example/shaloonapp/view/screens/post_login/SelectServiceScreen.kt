package com.example.shaloonapp.view.screens.post_login

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.shaloonapp.R
import com.example.shaloonapp.model.dto.Service
import com.example.shaloonapp.ui.theme.Purple40
import com.example.shaloonapp.ui.theme.SelectServiceScreen_TitleScreen_BackGround
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes.SELECT_TIME_SCREEN
import com.example.shaloonapp.view.util.getImgURLFromFirebase
import com.example.shaloonapp.viewmodel.PostLoginSharedViewModel
import com.example.shaloonapp.viewmodel.SelectServiceScreenViewModel
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun PreviewSelectServiceScreen(){
    SelectServiceScreen(rememberNavController(), viewModel())
}

@Composable
fun SelectServiceScreen(
    navController: NavHostController,
    postLoginSharedViewModel: PostLoginSharedViewModel
) {

    val context = LocalContext.current
    val selectServiceScreenViewModel: SelectServiceScreenViewModel = hiltViewModel()

    val listOfService = selectServiceScreenViewModel.listOfService.collectAsState()

    var selectedService by remember { mutableStateOf<Service?>(null) }

    postLoginSharedViewModel.currentService.value = selectedService

    var showDialog =  remember { mutableStateOf<Boolean>(false) }

    if(showDialog.value){
        DialogWithImage(service = selectedService?: Service(), setShowDialog = {
            showDialog.value = it
        }, imageDescription = "dsafads")
    }

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

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
            .padding(start = 8.dp, top = 10.dp, end = 8.dp)
            .constrainAs(serviceContainer) {
                bottom.linkTo(btnChooseDate.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(screenTitle.bottom)

                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }){
           items(listOfService.value ?:ArrayList<Service>()){
               service ->
               ServiceViewHolder(
                   service = service,
                   selectedService = selectedService,
                   onServiceSelected = {selectedService = it},
                   setShowDialog = {showDialog.value = it}
               )
           }
        }

        Button(
            onClick = {
                selectedService?.durationInMinute?.let {
                    // "SelectTimeScreen/{ServiceTime}"
                    // "SelectTimeScreen/60"
                    navController.navigate(
                        SELECT_TIME_SCREEN
                            .replace(
                                oldValue = "{serviceTime}",
                                newValue = "$it"
                            )
                    )
                   // Log.i("SelectServiceScreen",  postLoginSharedViewModel.currentService.value.toString())
                } ?: Toast.makeText(context,"Please Select Service", Toast.LENGTH_SHORT).show()
            },
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
            Text(text = stringResource(R.string.choose_date))
        }
        Button(
            onClick = {
                      navController.popBackStack()
            },
            colors = ButtonDefaults.buttonColors(Color.Red),
            modifier = Modifier
                .padding(20.dp)
                .constrainAs(btnAnotherBarber) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                }
        ) {
            Text(text = stringResource(R.string.choose_another_barber))
        }

    }
}

@Composable
fun ServiceViewHolder(
    service: Service,
    selectedService: Service?,
    setShowDialog: (Boolean) -> Unit,
    onServiceSelected: (Service) -> Unit){

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
                OutlinedButton(
                    onClick = {
                        onServiceSelected(service)
                        setShowDialog(true)      },
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
@Composable
fun DialogWithImage(
    service: Service,
    setShowDialog: (Boolean) -> Unit,
    imageDescription: String,
) {

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

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                    model = imgURL,
                    contentDescription = "service img",
                    placeholder = painterResource(R.drawable.ic_launcher_background),
                    error= painterResource(R.drawable.no_img_error),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.height(160.dp)
                )
                Text(text = service.name,
                    modifier = Modifier
                        .padding(15.dp)
                        )
                Text(
                    text = service.description,
                    modifier = Modifier.padding(16.dp),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { setShowDialog(false) },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }
                }
            }
        }
    }
}