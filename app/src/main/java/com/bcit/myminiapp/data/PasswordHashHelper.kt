package com.bcit.myminiapp.data
import org.mindrot.jbcrypt.BCrypt


fun hashPassword(password: String): String {
    return BCrypt.hashpw(password, BCrypt.gensalt())
}

fun verifyPassword(storedHash: String, inputPassword: String): Boolean {
    return BCrypt.checkpw(inputPassword, storedHash)
}