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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bcit.myminiapp.states.BattleState
import com.bcit.myminiapp.states.PlayerAndPokemonState
import com.bcit.myminiapp.states.RandomPokemonState

@Composable
fun PokemonCatchResult(navController: NavController) {
    val playerAndPokemonState: PlayerAndPokemonState = viewModel(viewModelStoreOwner = LocalActivity.current as ComponentActivity)

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            if (playerAndPokemonState.catchSuccess == true) CatchSuccess(navController)
            else CatchFail(navController)
        }
    }
}

@Composable
fun CatchSuccess(navController: NavController) {
    val randomPokemonState: RandomPokemonState = viewModel(viewModelStoreOwner = LocalActivity.current as ComponentActivity)

    MyText("Success! You caught ${randomPokemonState.randomPokemon?.name}!")
    MyImage(randomPokemonState.randomPokemon?.image)
    MyButton({ navController.navigate("home") }, "View your pokemons")
    MyButton(
        { navController.navigate("pokeworld") },
        "Go into the wild and catch more Pokemons!"
    )
}

@Composable
fun CatchFail(navController: NavController) {
    val randomPokemonState: RandomPokemonState = viewModel(viewModelStoreOwner = LocalActivity.current as ComponentActivity)
    val playerAndPokemonState: PlayerAndPokemonState = viewModel(viewModelStoreOwner = LocalActivity.current as ComponentActivity)

    MyText("You throw your Pokeball at ${randomPokemonState.randomPokemon?.name} but it broke free!")
    if (playerAndPokemonState.pokemons.size > 0) ChooseBattle(navController)
    MyButton(
        { navController.navigate("pokeworld") },
        "Go into the wild and catch more Pokemons!"
    )
}

@Composable
fun ChooseBattle(navController: NavController) {
    val randomPokemonState: RandomPokemonState = viewModel(viewModelStoreOwner = LocalActivity.current as ComponentActivity)
    val playerAndPokemonState: PlayerAndPokemonState = viewModel(viewModelStoreOwner = LocalActivity.current as ComponentActivity)
    val battleState: BattleState = viewModel(viewModelStoreOwner = LocalActivity.current as ComponentActivity)

    MyText("Choose a Pokemon to battle ${randomPokemonState.randomPokemon?.name}")
    PokemonStats(randomPokemonState.randomPokemon!!)
    LazyColumn(
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        items(playerAndPokemonState.pokemons.size) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                MyButton({
                    battleState.initializeBattle(playerAndPokemonState.pokemons[it], randomPokemonState.randomPokemon!!)
                    navController.navigate("battle")
                }, playerAndPokemonState.pokemons[it].name)
                PokemonStats(playerAndPokemonState.pokemons[it])
            }
        }
    }
}

