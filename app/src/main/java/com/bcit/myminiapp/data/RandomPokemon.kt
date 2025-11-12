package com.bcit.myminiapp.data

import com.google.gson.annotations.SerializedName

data class RandomPokemon (
    val id: String,
    val name: String,
    val sprites: Sprites,
    val stats: List<StatItem>
)

data class StatItem (
    @SerializedName("base_stat")
    val statValue: Int,
    val stat: Stat
)

data class Stat (
    val name: String
)

data class Sprites (
    val other: Other
)

data class Other (
    @SerializedName("official-artwork")
    val officialArt: OfficialArt
)

data class OfficialArt (
    @SerializedName("front_default")
    val image: String?
)