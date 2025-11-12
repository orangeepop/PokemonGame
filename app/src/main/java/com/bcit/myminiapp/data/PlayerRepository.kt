package com.bcit.myminiapp.data

class CustomException(message: String) : Exception(message)

class PlayerRepository(private val playerDao: PlayerDao) {
    fun insertEntity(player: Player) {
        playerDao.add(player)
    }

    fun login(playerName: String, password: String): Player {
        val player = playerDao.login(playerName) ?: throw CustomException("Invalid username")
        val passwordMatches = verifyPassword(player.password, password)
        if (!passwordMatches) throw CustomException("Wrong password")
        return player
    }
}