package com.bcit.myminiapp.data

import com.google.gson.Gson
import com.google.gson.JsonObject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class RandomPokemonRepository(private val httpClient: HttpClient) {
    suspend fun getPokemon(id: Int): Pokemon {
        val response = httpClient.get(POKEMON_API.format(id))
        val json = response.body<JsonObject>().toString()
        val initialPokemon = Gson().fromJson(json, RandomPokemon::class.java)
        return convertToPokemon(initialPokemon)
    }

    private fun convertToPokemon(randomPokemon: RandomPokemon): Pokemon {
        return Pokemon(
            apiID=randomPokemon.id,
            name=randomPokemon.name,
            image=randomPokemon.sprites.other.officialArt.image ?: "",
            hp=randomPokemon.stats.find { it.stat.name == "hp" }?.statValue,
            attack=randomPokemon.stats.find { it.stat.name == "attack" }?.statValue,
            defense=randomPokemon.stats.find { it.stat.name == "defense" }?.statValue,
            currentHp=randomPokemon.stats.find { it.stat.name == "hp" }?.statValue
        )
    }
}