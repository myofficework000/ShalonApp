package com.example.shaloonapp.view.screens.post_login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shaloonapp.ui.theme.SelectServiceScreen_TitleScreen_BackGround
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("NewApi")
@Composable
fun SelectServiceTimeSlot(navController: NavController, serviceTime: Int = 45){
    val timeSlots = populateTimeSlots()
    val selectedTime =  remember {
        mutableStateOf(LocalTime.of(0,0))
    }
    val days = getNextSevenDates().map {
        SimpleDateFormat("EEE MM-dd-yyyy", Locale.US).format(it.time)
    }
    val currentDayIndex = remember {
        mutableStateOf(0)
    }
    val selectedOptionText = remember { mutableStateOf(days[currentDayIndex.value]) }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column() {
            TopAppBar(title = {
                Text(text = "Select Time",
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center)
            },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SelectServiceScreen_TitleScreen_BackGround)

            )
            DatePicker(date = selectedOptionText.value,
                onPrevious = {
                             if(currentDayIndex.value > 0){
                                 currentDayIndex.value = currentDayIndex.value -1
                                 selectedOptionText.value = days[currentDayIndex.value]
                             }

            }, {
                if (currentDayIndex.value < days.size-1){
                    currentDayIndex.value = currentDayIndex.value +1
                    selectedOptionText.value = days[currentDayIndex.value]
                }

            }
            )

            Text(
                text = "Select time",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 8.dp),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.ExtraBold
            )
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
                    .weight(0.8F),
                verticalArrangement = Arrangement.Top,
                columns = GridCells.Fixed(4)
            ){
                items(timeSlots){
                    TimeSlot(it, serviceTime, selectedTime.value, selectedOptionText.value){ time ->
                        selectedTime.value = time
                    }
                }
            }
            Row(modifier = Modifier.padding(8.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = {
                    navController.popBackStack()
                }) {
                    Text(text = "Choose another service")
                }
                Button(
                    onClick = {
                        Log.i("Selected time slot",
                            "${selectedOptionText.value}," +
                                    " ${selectedTime.value.hour.toString().padStart(2, '0')}: " +
                                    "${selectedTime.value.minute.toString().padStart(2, '0')} -" +
                                    " ${selectedTime.value.plusMinutes(serviceTime.toLong()).hour.toString().padStart(2, '0')}" +
                                    " ${selectedTime.value.plusMinutes(serviceTime.toLong()).minute.toString().padStart(2, '0')}")
                    }
                ) {
                    Text(text = "Next")
                }
            }
        }

    }
}

@SuppressLint("NewApi")
@Composable
fun TimeSlot(time: LocalTime, serviceTime: Int,  selectedTime: LocalTime, selectedOptionText: String,  onSelectedTime:(LocalTime) -> Unit){
    val isSelectedDateToday = SimpleDateFormat("EEE MM-dd-yyyy", Locale.US).format(Calendar.getInstance().time) == selectedOptionText
    Surface(
        modifier = Modifier
            .wrapContentHeight()
            .clickable {
                if (LocalTime.now() > time && isSelectedDateToday) {
                    // Time past working hours.
                } else {
                    onSelectedTime(time)
                }
            },
        border = BorderStroke(1.dp, Color.LightGray),
        color = if(selectedTime.until(time, ChronoUnit.MINUTES) in 0 until serviceTime) Color.Blue.copy(alpha = 0.3F)
        else if(LocalTime.now() > time &&  isSelectedDateToday) Color.LightGray
        else Color.White
    ) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 2.dp),
            text = DateTimeFormatter.ofPattern("hh:mm a").format(time),
            color = Color.Black,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

fun getNextSevenDates(): MutableList<Calendar> {
    val days = mutableListOf<Calendar>()
    for (i in 0..7){
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, i)
        days.add(calendar)
    }
    return days
}

@Composable
fun DatePicker(
    date: String,
    onPrevious: () -> Unit,
    onNext: () -> Unit
){
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = Icons.Rounded.KeyboardArrowLeft,
            contentDescription = null,
            tint = Color.Blue.copy(0.5F),
            modifier = Modifier.clickable {
                onPrevious()
            }.size(32.dp)
        )
        Text(
            text = date,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .weight(0.5F)
        )
        Icon(
            imageVector = Icons.Rounded.KeyboardArrowRight,
            contentDescription = null,
            tint = Color.Blue.copy(0.5F),
            modifier = Modifier.clickable {
                onNext()
            }.size(32.dp)
            )
    }
}

@SuppressLint("NewApi")
fun populateTimeSlots(): List<LocalTime>{
    val openingTime = LocalTime.of(9, 0)
    val listOfSlots = mutableListOf<LocalTime>()
    for(i in 0 until 8 ){
        for(j in 0 .. 3){
            val timeSlot1 = LocalTime.of(openingTime.hour+i, j*15)
            listOfSlots.add(timeSlot1)
        }
    }
    return listOfSlots
}