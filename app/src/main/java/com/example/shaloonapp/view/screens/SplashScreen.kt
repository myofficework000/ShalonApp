package com.example.shaloonapp.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.shaloonapp.R
import com.example.shaloonapp.ui.theme.Purple40
import com.example.shaloonapp.ui.theme.Yellow
import com.example.shaloonapp.view.navigation.PreLoginNavRoutes.LOGIN_SCREEN
import com.example.shaloonapp.view.navigation.PreLoginNavRoutes.REGISTER_SCREEN


@Composable
fun SplashScreen(navController: NavController){

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Purple40),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){

        val rawComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.animation))

        Text(
            modifier = Modifier
                .padding(top = 50.dp, start = 10.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium,

            text = stringResource(id = R.string.Lets_Get_Started)

        )

        LottieAnimation(
            composition = rawComposition,
            modifier = Modifier
                .size(500.dp)
                .padding(top = 50.dp)
            ,
            contentScale = ContentScale.Inside,
            iterations = Int.MAX_VALUE
        )

        Button(
            onClick = {
                navController.navigate(REGISTER_SCREEN)
            },
            shape = RoundedCornerShape(8.dp),
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

        TextButton(onClick = { navController.navigate(LOGIN_SCREEN) }) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp),
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodySmall,
                text = stringResource(id = R.string.Aleardy_have_an_account_Login)
            )
        }



    }
}