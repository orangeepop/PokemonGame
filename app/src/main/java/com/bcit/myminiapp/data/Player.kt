package com.bcit.myminiapp.data

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity(tableName = "player_table")
data class Player (
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "player_name") val playerName: String?,
    val password: String
)

@Dao
interface PlayerDao {
    @Query("SELECT * FROM player_table WHERE player_name = :playerName")
    fun login(playerName: String): Player?

    @Insert
    fun add(player: Player)
}

@Database(entities = [Player::class, Pokemon::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun pokemonDao(): PokemonDao
}

object MyDatabase {
    fun getDatabase(context: Context) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "my_db")
            .allowMainThreadQueries()
            .build()
    }
}