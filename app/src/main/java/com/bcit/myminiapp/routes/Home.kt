package com.bcit.myminiapp.routes

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bcit.myminiapp.states.PlayerAndPokemonState
import com.bcit.myminiapp.R

@Composable
fun Home(navController: NavController) {
    val playerAndPokemonState:PlayerAndPokemonState = viewModel(viewModelStoreOwner = LocalActivity.current as ComponentActivity)

    if (playerAndPokemonState.player == null) {
        Landing(navController)
    } else {
        PlayerAccount(navController)
    }
}

@Composable
fun Landing(navController: NavController) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize() ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.pokemon),
                contentDescription = ""
            )
            MyButton({navController.navigate("signup")}, "Sign up")
            MyButton({navController.navigate("login")}, "Log in")
        }
    }
}

@Composable
fun PlayerAccount(navController: NavController) {
    val playerAndPokemonState: PlayerAndPokemonState = viewModel(viewModelStoreOwner = LocalActivity.current as ComponentActivity)

    Column(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        playerAndPokemonState.getCaughtPokemons()
        MyText("Welcome, ${playerAndPokemonState.player?.playerName!!}")
        MyButton({playerAndPokemonState.logout()}, "Logout")

        if (playerAndPokemonState.pokemons.size > 0) DisplayPokemonRoster()
        else RedirectToPokeWorld(navController)
    }
}

@Composable
fun DisplayPokemonRoster() {
    val playerAndPokemonState: PlayerAndPokemonState = viewModel(viewModelStoreOwner = LocalActivity.current as ComponentActivity)

    MyText("Your pokemon roster:")
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        items(playerAndPokemonState.pokemons.size) {
            MyImage(playerAndPokemonState.pokemons[it].image)
            MyText(playerAndPokemonState.pokemons[it].name)
            PokemonStats(playerAndPokemonState.pokemons[it])
        }
    }
}

@Composable
fun RedirectToPokeWorld(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MyText("Looks like your Pokemon roster is empty! Go out there and catch some Pokemons!")
            MyButton({ navController.navigate("pokeworld") }, "Go explore")
        }
    }
}