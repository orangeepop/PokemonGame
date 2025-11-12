package com.bcit.myminiapp.routes

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bcit.myminiapp.states.BattleState
import com.bcit.myminiapp.states.PlayerAndPokemonState

@Composable
fun Battle(navController: NavController) {
    val battleState: BattleState = viewModel(viewModelStoreOwner = LocalActivity.current as ComponentActivity)

    LaunchedEffect(battleState) {
        while (battleState.winner == null) {
            battleState.battle()
        }
    }

    BattleUI(navController)
}

@Composable
fun BattleUI(navController: NavController) {
    val battleState: BattleState =
        viewModel(viewModelStoreOwner = LocalActivity.current as ComponentActivity)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BattleDamageUI()
            if (battleState.winner != null) {
                BattleResultUI()
                MyButton({ navController.navigate("home") }, "View your pokemons")
                MyButton(
                    { navController.navigate("pokeworld") },
                    "Go into the wild and catch more Pokemons!"
                )
            }
        }
    }
}

@Composable
fun BattleDamageUI() {
    val battleState: BattleState =
        viewModel(viewModelStoreOwner = LocalActivity.current as ComponentActivity)

    Column {
        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            MyImage(battleState.opponentPokemon?.image)
            MyImage(battleState.pokemon?.image)
        }

        battleState.pokemonAttack?.let {
            MyText(
                """${battleState.pokemon?.name} dealt $it damage to ${battleState.opponentPokemon?.name}! 
                    ${battleState.opponentPokemon?.name} has ${battleState.opponentPokemon?.currentHp} hp remaining.
                """.trimIndent().replace("\n", "")
            )
        }

        battleState.opponentPokemonAttack?.let {
            MyText(
                """${battleState.opponentPokemon?.name} dealt $it damage to ${battleState.pokemon?.name}! 
                    ${battleState.pokemon?.name} has ${battleState.pokemon?.currentHp} hp remaining.
                """.trimIndent().replace("\n", "")
            )
        }
    }
}

@Composable
fun BattleResultUI() {
    val battleState: BattleState =
        viewModel(viewModelStoreOwner = LocalActivity.current as ComponentActivity)
    val playerAndPokemonState: PlayerAndPokemonState =
        viewModel(viewModelStoreOwner = LocalActivity.current as ComponentActivity)

    battleState.winner?.let {
        MyImage(it.image)
        MyText("${it.name} wins!")

        if (it == battleState.pokemon) {
            playerAndPokemonState.catchPokemonAfterBattle(battleState.opponentPokemon!!)
            MyText("You throw your Pokeball at an exhausted " +
                    "${battleState.opponentPokemon?.name} and successfully catch it!")
        } else {
            MyText(
                """
                You throw a Pokeball in a last-ditch effort, 
                but it fails to catch the wild Pokemon as it escapes into the wild!
                """.trimIndent().replace("\n", "")
            )
        }
    }
}

