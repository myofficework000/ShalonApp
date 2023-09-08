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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shaloonapp.R
import com.example.shaloonapp.ui.theme.Purple40
import com.example.shaloonapp.ui.theme.Yellow
import com.example.shaloonapp.view.screens.components.RoundedCard
import com.example.shaloonapp.view.screens.components.TextInput


@Composable
fun RegisterScreen(navController: NavController) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Purple40),
        horizontalAlignment = Alignment.CenterHorizontally
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
            .padding(top = 10.dp))
    }

}

@Composable
fun CardView(modifier: Modifier){

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    RoundedCard(modifier = modifier.fillMaxWidth()) {

        Column(modifier = Modifier.padding(top = 24.dp)) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                text = stringResource(id = R.string.userName)
            )

            TextInput(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
                text = userName, onValueChange = { userName = it },
                label =stringResource(id = R.string.enter_your_name)
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
                .fillMaxWidth()
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
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
                text = email, onValueChange = { email = it },
                label = stringResource(id = R.string.enter_your_Email)

            )

            Button(
                onClick = {
                    TODO()
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
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodySmall,
                text = stringResource(id = R.string.Aleardy_have_an_account_Login)
            )

        }

    }

}