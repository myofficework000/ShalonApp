package com.example.shaloonapp.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.shaloonapp.R
import com.example.shaloonapp.model.dto.User
import com.example.shaloonapp.ui.theme.Purple40
import com.example.shaloonapp.ui.theme.Yellow
import com.example.shaloonapp.view.navigation.PreLoginNavRoutes
import com.example.shaloonapp.view.navigation.PreLoginNavRoutes.LOGIN_SCREEN
import com.example.shaloonapp.view.navigation.PreLoginNavRoutes.REGISTER_SCREEN
import com.example.shaloonapp.view.screens.components.RoundedCard
import com.example.shaloonapp.view.screens.components.TextInput
import com.example.shaloonapp.viewmodel.RegisterViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@Composable
fun RegisterScreen(navController: NavController) {

    val registerViewModel :RegisterViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Purple40)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Image(
            modifier = Modifier
                .padding(top = 80.dp)
                .size(200.dp)
                .clip(CircleShape),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.barabar_image), contentDescription = "image"
        )
        CardView(modifier = Modifier
            .fillMaxHeight()
            .padding(top = 10.dp),
                viewModel = registerViewModel,
            coroutineScope = coroutineScope,
            navController = navController
            )
    }

}

@Composable
fun CardView(modifier: Modifier, viewModel: RegisterViewModel, coroutineScope: CoroutineScope, navController: NavController){


    var registerUser by remember{
        mutableStateOf("Result")
    }

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    RoundedCard(modifier = modifier.fillMaxWidth()) {

        Column(modifier = Modifier.padding(top = 24.dp)) {

            Text(modifier = Modifier.testTag("TextRegister"),
                text = registerUser)

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                text = stringResource(id = R.string.first_name)
            )

            TextInput(modifier = Modifier
                .fillMaxWidth().testTag("firstName")
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
                text = firstName, onValueChange = { firstName = it },
                label =stringResource(id = R.string.enter_your_first_name)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                text = stringResource(id = R.string.last_name)
            )

            TextInput(modifier = Modifier
                .fillMaxWidth().testTag("lastName")
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
                text = lastName, onValueChange = { lastName = it },
                label =stringResource(id = R.string.enter_your_last_name)
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                text = stringResource(id = R.string.password)
            )

            TextInput(modifier = Modifier
                .fillMaxWidth().testTag("password")
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
                text = password, onValueChange = { password = it },
                label =stringResource(id = R.string.enter_your_password),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                text = stringResource(id = R.string.Email)

            )

            TextInput(modifier = Modifier
                .fillMaxWidth().testTag("email")
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
                text = email, onValueChange = { email = it },
                label = stringResource(id = R.string.enter_your_Email)

            )

            Button(
                onClick = {
                    doRegister(email = email, password = password,firstName =firstName,
                        lastName = lastName,
                        {
                            registerUser = "Successful"
                            coroutineScope.launch {
                                registerUser(firstName, lastName, password, email, viewModel)
                            }
                            navController.navigate(LOGIN_SCREEN){
                                popUpTo(REGISTER_SCREEN){
                                    inclusive = true
                                }
                            }

                        },{
                            registerUser = "Invalid credentials"
                        })

                },
                shape= RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Yellow),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.SignUp),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                )
            }
            TextButton(onClick = {
                navController.navigate(LOGIN_SCREEN){
                    popUpTo(REGISTER_SCREEN){
                        inclusive = true
                    }
                }
            }) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, start = 10.dp, bottom = 8.dp),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodySmall,
                    text = stringResource(id = R.string.Aleardy_have_an_account_Login)
                )
            }

        }

    }

}

fun doRegister(email:String,password:String,firstName: String,
               lastName: String,
               onSuccess: ()-> Unit, onError: ()-> Unit){
    val existingUsers = listOf("test1@gmail.com","test2@gmail.com","test3@gmail.com")

    if(email.isEmpty() ||password.isEmpty() || firstName.isEmpty()||lastName.isEmpty()){
        onError()
    }

    else if(email in existingUsers){
        onError()
    }
    else if(password.length <5){
        onError()
    }
    else if(!email.contains("@")){
        onError()
    }
    else {
        onSuccess()
    }
}

private suspend fun registerUser(
    firstName:String,
    lastName:String,
    password:String,
    email:String,
    viewModel: RegisterViewModel
){
    val user = User(
        firstName = firstName,
        lastName = lastName,
        password = password,
        email = email
    )
    viewModel.insertUser(user)
}