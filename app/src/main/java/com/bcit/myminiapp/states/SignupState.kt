package com.bcit.myminiapp.states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignupState: ViewModel() {
    var playerName by mutableStateOf("")
    val onPlayerNameChanged: (String) -> Unit = {
        playerName = it
    }

    var password by mutableStateOf("")
    var onPasswordChanged: (String) -> Unit = {
        password = it
    }

    fun clearFields() {
        playerName = ""
        password = ""
    }
}