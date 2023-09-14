package com.example.shaloonapp.view.screens.post_login

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.shaloonapp.R
import com.example.shaloonapp.ui.theme.Purple40
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberMarkerState
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SaloonDetailsScreen(){

    val context = LocalContext.current
    val scrollState = rememberScrollState()
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
                .verticalScroll(
                    state = scrollState
                )
                .fillMaxSize()
        ) {
            Details(modifier = Modifier,context = context)
        }
    }

}


@Composable
fun Details(modifier : Modifier, context: Context){



    Column {


        Text(modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp), text = "Contact Person",
            fontWeight = FontWeight.Bold)
        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp),
            text = "David Miller",
            color = Purple40)

        Divider( modifier = Modifier)

        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp), text = "Shop time",
            fontWeight = FontWeight.Bold
            )
        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp), text = "Morning 8 to evening 9.30",
            color = Purple40)

        Divider( modifier = Modifier)

        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp), text = "Contact Number",
            fontWeight = FontWeight.Bold)

        Row {
            Text(modifier = Modifier
                .fillMaxWidth().weight(0.8F)
                .padding(top = 10.dp, start = 10.dp), text = "7038264899",
                color = Color.Gray)
            Image(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable {
                        val phone = "+34666777888"
                        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
                        context.startActivity(intent)
                    }
                    .size(24.dp),
                painter = painterResource(id = R.drawable.baseline_local_phone_24),
                contentDescription = "image",
                contentScale = ContentScale.FillBounds,
            )
        }
        Divider( modifier = Modifier)
        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp), text = "Shop Address",
            fontWeight = FontWeight.Bold)

        Row {
            Text(modifier = Modifier
                .fillMaxWidth().weight(0.8F)
                .padding(top = 10.dp, start = 10.dp), text = "Virginia",
                color = Color.Gray)
            Image(modifier = Modifier.clickable {
                getLocation(context,  38.969730F, -77.38387F)
            }
                .padding(end = 8.dp)
                .size(24.dp),painter = painterResource(id = R.drawable.baseline_location_on_24),
                contentDescription ="image")
        }
        Divider( modifier = Modifier)

        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp), text = "Facebook",
            fontWeight = FontWeight.Bold)

        Row {
            Text(modifier = Modifier
                .fillMaxWidth().weight(0.8F)
                .padding(top = 10.dp, start = 10.dp), text = "www.facebook.com/SalonPurple",
                color = Color.DarkGray)
            Image(modifier = Modifier
                .padding(end = 8.dp)
                .size(24.dp),painter = painterResource(id = R.drawable.baseline_attachment_24),
                contentDescription ="image")
        }
        Divider( modifier = Modifier)

        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp), text = "Email",
            fontWeight = FontWeight.Bold)

        Row {
            Text(modifier = Modifier
                .fillMaxWidth().weight(0.8F)
                .padding(top = 10.dp, start = 10.dp), text = "psmobtech@gmail.com",
                color = Color.Gray)
            Image(modifier = Modifier.clickable {
                composeEmail(context, "pavanivelma@gmail.com","null","null")
            }
                .padding(end = 8.dp)
                .size(24.dp),painter = painterResource(id = R.drawable.baseline_email_24),
                contentDescription ="image",
                alignment = Alignment.Center)
        }
        Divider( modifier = Modifier)
        Map()
    }

}

@Composable
fun Map(){
    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        uiSettings = MapUiSettings(zoomControlsEnabled = true),
        cameraPositionState = CameraPositionState(
            CameraPosition(LatLng(38.969730, -77.383873), 12f, 0f,0f)
        )
    ){
        Marker(
            state = rememberMarkerState(
                position = LatLng(38.969730, -77.383873)
            )
        )
    }
}


@Composable
fun Divider(modifier : Modifier){

    androidx.compose.material3.Divider(
        color = Color.Black, modifier = modifier
            .fillMaxWidth()
            .width(1.dp)
            .padding(top = 5.dp)
    )
}


fun composeEmail(context: Context, email:String, subject:String, text:String){

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_EMAIL, email)
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(intent)
}

fun getLocation(context: Context, latitude: Float,longitude:Float){
    val uri: String = java.lang.String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude)
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    context.startActivity(intent)
}



