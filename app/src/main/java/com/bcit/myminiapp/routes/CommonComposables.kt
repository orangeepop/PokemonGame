package com.bcit.myminiapp.routes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.bcit.myminiapp.data.Pokemon

@Composable
fun MyButton(onClickAction: () -> Unit, text: String) {
    FilledTonalButton(
        onClick = onClickAction,
        colors = ButtonColors(
            containerColor = Color(0xFF3363AF), contentColor = Color.White,
            disabledContainerColor = Color.White,
            disabledContentColor = Color.White),
        modifier = Modifier.padding(5.dp)) {
        Text(text)
    }
}

@Composable
fun MyTextField(value: String,
                onValueChanged: (String) -> Unit) {
    TextField(
        value,
        onValueChange = onValueChanged,
        textStyle = TextStyle(fontSize=30.sp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFA8CCED),
            focusedContainerColor = Color(0xFFC7DDF2),
            focusedBorderColor = Color(0xFF3363AF)
        ),
        modifier = Modifier.padding(2.dp)
    )
}

@Composable
fun MyText(value: String) {
    Text(text = value,
        textAlign = TextAlign.Center,
        color = Color(0xFF3363AF),
        fontSize = 20.sp,
        modifier = Modifier.padding(10.dp)
    )
}

@Composable
fun PokemonStats(pokemon: Pokemon) {
    Row(
        modifier = Modifier.padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        StatsText("HP: ${pokemon.hp ?: 0}")
        StatsText("ATK: ${pokemon.attack ?: 0}")
        StatsText("DEF: ${pokemon.defense ?: 0}")
    }
}

@Composable
fun StatsText(value: String) {
    Text(text = value,
        textAlign = TextAlign.Center,
        color = Color(0xFF3363AF),
        fontSize = 15.sp,
        modifier = Modifier.padding(horizontal = 10.dp)
    )
}

@Composable
fun MyImage(url: String?) {
    AsyncImage(model = url, contentDescription = null)
}