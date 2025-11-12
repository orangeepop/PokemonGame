package com.bcit.myminiapp.states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bcit.myminiapp.data.Pokemon
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.random.Random

class BattleState: ViewModel() {
    var pokemon by mutableStateOf<Pokemon?>(null)
    var opponentPokemon by mutableStateOf<Pokemon?>(null)
    var pokemonAttack by mutableStateOf<Int?>(null)
    var opponentPokemonAttack by mutableStateOf<Int?>(null)
    var winner by mutableStateOf<Pokemon?>(null)

    fun initializeBattle(newPokemon: Pokemon, newOpponentPokemon: Pokemon) {
        pokemon = newPokemon
        opponentPokemon = newOpponentPokemon
        pokemon?.currentHp = pokemon?.hp
        opponentPokemon?.currentHp = opponentPokemon?.hp
        pokemonAttack = null
        opponentPokemonAttack = null
        winner = null
    }

    suspend fun battle() {
        // Pokemon attacks first
        pokemonAttack = determineDamage(pokemon?.attack ?: 0, opponentPokemon?.defense ?: 0)
        opponentPokemon?.currentHp = opponentPokemon?.currentHp?.let {
            max(it - pokemonAttack!!, 0)
        }
        if (determineWinner()) return
        delay(1000L)

        // Opponent attacks
        opponentPokemonAttack = determineDamage(opponentPokemon?.attack ?: 0, pokemon?.defense ?: 0)
        pokemon?.currentHp = pokemon?.currentHp?.let {
            max(it - opponentPokemonAttack!!, 0)
        }
        if (determineWinner()) return
        delay(1000L)
    }

    private fun determineDamage(attack: Int, defense: Int): Int {
        val damage = attack + Random.nextInt(-10, 11) - defense
        return max(damage, 0)
    }

    private fun determineWinner(): Boolean {
        if (pokemon?.currentHp!! <= 0) {
            winner = opponentPokemon
            return true
        } else if (opponentPokemon?.currentHp!! <= 0) {
            winner = pokemon
            return true
        }
        return false
    }
}