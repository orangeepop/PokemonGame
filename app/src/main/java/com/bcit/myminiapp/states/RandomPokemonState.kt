package com.bcit.myminiapp.states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bcit.myminiapp.data.Pokemon
import com.bcit.myminiapp.data.RandomPokemonRepository
import com.bcit.myminiapp.data.TOTAL_POKEMONS
import kotlin.random.Random

class RandomPokemonState(private val randomPokemonRepository: RandomPokemonRepository): ViewModel() {
    var randomPokemon by mutableStateOf<Pokemon?>(null)

    suspend fun getPokemon() {
        val randomId = Random.nextInt(1, TOTAL_POKEMONS)
        randomPokemon = randomPokemonRepository.getPokemon(randomId)
    }
}