package com.bcit.myminiapp.routes

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bcit.myminiapp.states.PlayerAndPokemonState
import com.bcit.myminiapp.states.SignupState
import com.bcit.myminiapp.data.Player
import com.bcit.myminiapp.data.hashPassword

@Composable
fun LoginOrSignUp(navController: NavController, isLogin: Boolean) {
    val signupState: SignupState = viewModel()
    val playerAndPokemonState: PlayerAndPokemonState = viewModel(viewModelStoreOwner = LocalActivity.current as ComponentActivity)

    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyTextField(signupState.playerName, signupState.onPlayerNameChanged)
            MyTextField(signupState.password, signupState.onPasswordChanged)
            if (isLogin) {
                MyButton(
                    {
                        playerAndPokemonState.login(signupState.playerName, signupState.password)
                        signupState.clearFields()
                        navController.navigate("home")
                    },
                    "Login"
                )
            } else {
                MyButton(
                    {
                        playerAndPokemonState.add(Player(playerName = signupState.playerName, password = hashPassword(signupState.password)))
                        signupState.clearFields()
                        navController.navigate("login")
                    },
                    "Sign up"
                )
            }

        }
    }
}