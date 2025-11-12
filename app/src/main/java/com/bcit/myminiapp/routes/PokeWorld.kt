package com.bcit.myminiapp.routes

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bcit.myminiapp.states.PlayerAndPokemonState
import com.bcit.myminiapp.states.RandomPokemonState

@Composable
fun PokeWorld(navController: NavController) {
    val playerAndPokemonState: PlayerAndPokemonState = viewModel(viewModelStoreOwner = LocalActivity.current as ComponentActivity)

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (playerAndPokemonState.player == null) RedirectToLogin(navController)
        else RandomPokemonEncounter(navController)
    }
}

@Composable
fun RedirectToLogin(navController: NavController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(10.dp)) {
        Text(
            text = "You must login first!",
            color = Color(0xFF3363AF),
            fontSize = 40.sp
        )
        MyButton({navController.navigate("login")}, "Log in")
    }
}

@Composable
fun RandomPokemonEncounter(navController: NavController) {
    val playerAndPokemonState: PlayerAndPokemonState = viewModel(viewModelStoreOwner = LocalActivity.current as ComponentActivity)
    val randomPokemonState: RandomPokemonState = viewModel(viewModelStoreOwner = LocalActivity.current as ComponentActivity)

    LaunchedEffect(randomPokemonState) {
        randomPokemonState.getPokemon()
    }

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(10.dp)) {
        items(if (randomPokemonState.randomPokemon != null) 1 else 0) {
            val displayText: String =
                if (randomPokemonState.randomPokemon?.name != null) "A wild ${randomPokemonState.randomPokemon?.name} appears!"
                else "Error fetching pokemon name"

            MyImage(randomPokemonState.randomPokemon?.image)
            MyText(displayText)
            PokemonStats(randomPokemonState.randomPokemon!!)
            MyButton(
                {
                    randomPokemonState.randomPokemon?.let {playerAndPokemonState.catchPokemon(it)}
                    navController.navigate("pokemonCatchResult")
                },
                "Catch this pokemon!"
            )
        }
    }
}