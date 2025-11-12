package com.bcit.myminiapp.states

import androidx.compose.runtime.mutableStateOf
import com.bcit.myminiapp.data.CustomException
import com.bcit.myminiapp.data.Player
import com.bcit.myminiapp.data.PlayerRepository
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.bcit.myminiapp.data.Pokemon
import com.bcit.myminiapp.data.PokemonRepository
import kotlin.random.Random

class PlayerAndPokemonState(private val repository: PlayerRepository, private val pokemonRepository: PokemonRepository): ViewModel() {
    var player by mutableStateOf<Player?>(null)
    var pokemons = mutableStateListOf<Pokemon>()
    var catchSuccess by mutableStateOf<Boolean?>(null)

    fun add(player: Player) {
        repository.insertEntity(player)
    }

    fun login(playerName: String, password: String) {
        try {
            player = repository.login(playerName, password)
        } catch (e: CustomException) {
            e.message?.let { Log.i("login error", it) }
        }
    }

    fun logout() {
        player = null
    }

    fun getCaughtPokemons() {
        pokemons = pokemonRepository.getPokemonsByUserId(player?.uid).toMutableStateList()
    }

    fun catchPokemon(pokemon: Pokemon) {
        catchSuccess = Random.nextBoolean()
        pokemon.uid = player?.uid!!
        if (catchSuccess!!) pokemonRepository.insertPokemon(pokemon)
    }

    fun catchPokemonAfterBattle(pokemon: Pokemon) {
        pokemon.uid = player?.uid!!
        pokemonRepository.insertPokemon(pokemon)
    }
}