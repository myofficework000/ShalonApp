package com.example.shaloonapp.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.example.shaloonapp.model.ResultState
import com.example.shaloonapp.model.dto.User
import com.example.shaloonapp.model.utils.putStringInSecuredSharedPreferences
import com.example.shaloonapp.ui.theme.Purple40
import com.example.shaloonapp.ui.theme.Yellow
import com.example.shaloonapp.view.navigation.PostLoginNavRoutes
import com.example.shaloonapp.view.navigation.PreLoginNavRoutes.LOGIN_SCREEN
import com.example.shaloonapp.view.navigation.PreLoginNavRoutes.REGISTER_SCREEN
import com.example.shaloonapp.view.screens.components.RoundedCard
import com.example.shaloonapp.view.screens.components.TextInput
import com.example.shaloonapp.viewmodel.LoginViewModel


@Composable
fun LoginScreen(navController: NavController){

    val context = LocalContext.current
    val loginViewModel : LoginViewModel = hiltViewModel()
    val response = loginViewModel.user.collectAsState()
    val errorMessage = remember {
        mutableStateOf("")
    }

    LaunchedEffect(response.value){
        when(response.value){
            is ResultState.Success ->{
                (response.value as ResultState.Success<User>).body.let{
                    it?.let {user->
                        context.putStringInSecuredSharedPreferences("password", user.password)
                        context.putStringInSecuredSharedPreferences("email", user.email)

                        navController.navigate(PostLoginNavRoutes.HOME_SCREEN){
                            popUpTo(LOGIN_SCREEN){
                                inclusive = true
                            }
                        }
                    }?: kotlin.run{
                        errorMessage.value = "Invalid Email or Password."
                    }
                }
            }
            is ResultState.Loading -> {
            }
            else -> {
                errorMessage.value = "Invalid Email or Password."
            }
        }

    }

    LoginContent(loginViewModel = loginViewModel, errorMessage.value, navController)
}

@Composable
fun LoginContent(
    loginViewModel: LoginViewModel,
    errorMessage: String = "",
    navController: NavController
){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Purple40),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            modifier = Modifier
                .padding(top = 80.dp)
                .size(200.dp)
                .clip(CircleShape),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.barabar_image), contentDescription = "image"
        )
        CardView2(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 10.dp),
            navController = navController,
            loginViewModel = loginViewModel,
            errorMessage = errorMessage
        )
    }
}

@Composable
fun CardView2(
    modifier: Modifier,
    loginViewModel: LoginViewModel,
    errorMessage: String,
    navController: NavController,
    onLogin: () -> Unit = {}
){


    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    RoundedCard(modifier = modifier.fillMaxWidth(),) {

        Column {

            if(errorMessage.isNotEmpty()){
                Text(text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier
                        .testTag("Error Message")
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 10.dp),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineMedium,
                )
            }


            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                text = stringResource(id = R.string.email)

            )

            TextInput(modifier = Modifier
                .testTag("Input Email")
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
                text = userName,
                onValueChange = { userName = it },
                label = stringResource(id = R.string.enter_your_Email)

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
                .testTag("Input Password")
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
                text = password,
                onValueChange = { password = it },
                label = stringResource(id = R.string.enter_your_password),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )


            Button(
                onClick = {
                    GetUser(userName, password, loginViewModel)
                    onLogin()

                },
                colors = ButtonDefaults.buttonColors(Yellow),
                modifier = Modifier
                    .testTag("SignIn Button")
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.SignIn),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                )
            }
            TextButton(
                modifier = Modifier
                    .testTag("SignUp Button"),
                onClick = {
                navController.navigate(REGISTER_SCREEN){
                    popUpTo(LOGIN_SCREEN){
                        inclusive = true
                    }
                }
            }) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 10.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodySmall,
                    text = "Don't have the account? SignUp"

                )
            }



        }

    }

}




fun GetUser(
    email: String,
    password: String,
    loginViewModel: LoginViewModel,
){
    loginViewModel.getUser(email, password)
}


