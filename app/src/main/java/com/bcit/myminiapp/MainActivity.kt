package com.bcit.myminiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bcit.myminiapp.data.PokemonRepository
import com.bcit.myminiapp.data.MyDatabase
import com.bcit.myminiapp.data.PlayerRepository
import com.bcit.myminiapp.data.RandomPokemonRepository
import com.bcit.myminiapp.data.client
import com.bcit.myminiapp.routes.Battle
import com.bcit.myminiapp.routes.LoginOrSignUp
import com.bcit.myminiapp.routes.Home
import com.bcit.myminiapp.routes.PokeWorld
import com.bcit.myminiapp.routes.PokemonCatchResult
import com.bcit.myminiapp.states.BattleState
import com.bcit.myminiapp.states.PlayerAndPokemonState
import com.bcit.myminiapp.states.RandomPokemonState
import com.bcit.myminiapp.states.SignupState

class MainActivity : ComponentActivity() {
    private val db by lazy {
        MyDatabase.getDatabase(applicationContext)
    }
    private val playerRepo by lazy {
        PlayerRepository(db.playerDao())
    }

    private val randomPokemonRepo by lazy {
        RandomPokemonRepository(client)
    }
    private val caughtPokemonRepo by lazy {
        PokemonRepository(db.pokemonDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(modifier = Modifier.safeDrawingPadding()) {
                MainContent()
            }
        }
    }

    @Composable
    fun MainContent() {
        val navController = rememberNavController()
        viewModel { PlayerAndPokemonState(playerRepo, caughtPokemonRepo) }
        viewModel { SignupState() }
        viewModel { RandomPokemonState(randomPokemonRepo) }
        viewModel { BattleState() }

        Scaffold(
            bottomBar = { NavBar(navController) }
        ) { padding ->
            NavHost(
                navController,
                "home",
                modifier = Modifier.padding(padding)
            ) {
                composable("home") {
                    Home(navController)
                }

                composable("login") {
                    LoginOrSignUp(navController, true)
                }

                composable("signup") {
                    LoginOrSignUp(navController, false)
                }

                composable("pokeworld") {
                    PokeWorld(navController)
                }

                composable("pokemonCatchResult") {
                    PokemonCatchResult(navController)
                }

                composable("battle") {
                    Battle(navController)
                }
            }
        }
    }
}