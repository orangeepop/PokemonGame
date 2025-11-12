package com.bcit.myminiapp.data

class PokemonRepository(private val pokemonDao: PokemonDao) {
    fun insertPokemon(pokemon: Pokemon) {
        pokemonDao.insertPokemon(pokemon)
    }

    fun getPokemonsByUserId(uid: Int?): List<Pokemon> {
        if (uid != null)
            return pokemonDao.getPokemonsByUserId(uid)
        else
            return emptyList()
    }
}