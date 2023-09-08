package com.example.shaloonapp.view.screens.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    text: String,
    label:String,
    onValueChange:(String) -> Unit,
    keyboardOptions: KeyboardOptions  = KeyboardOptions(keyboardType = KeyboardType.Text)
){
    OutlinedTextField(modifier = modifier,
        value = text, onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        ),
        label = {
            Text(text = label)
        },
        keyboardOptions = keyboardOptions
    )
}

@Composable
fun RoundedCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit){
    Card(modifier = modifier,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ){
            content()
    }
}