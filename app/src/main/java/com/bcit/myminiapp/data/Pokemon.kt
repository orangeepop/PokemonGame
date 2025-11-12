package com.bcit.myminiapp.data

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(
    tableName = "caught_pokemons_table",
    foreignKeys = [
        ForeignKey(
            entity = Player::class,
            parentColumns = ["uid"],
            childColumns = ["uid"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Pokemon (
    @PrimaryKey(autoGenerate = true) val tableID: Int? = null,
    var uid: Int? = null,
    val apiID: String,
    val name: String,
    val image: String,
    var hp: Int?,
    var currentHp: Int?,
    val attack: Int?,
    val defense: Int?
)

@Dao
interface PokemonDao {
    @Query("SELECT * FROM caught_pokemons_table WHERE uid = :uid")
    fun getPokemonsByUserId(uid: Int): List<Pokemon>

    @Insert
    fun insertPokemon(pokemon: Pokemon)
}