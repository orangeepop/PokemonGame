package com.bcit.myminiapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class NavItem(val icon: ImageVector, val route: String)

@Composable
fun NavBar(navController: NavController) {
    val navItems = listOf(
        NavItem(Icons.Default.Home, "home"),
        NavItem(Icons.Default.Star, "pokeworld")
    )

    NavigationBar(
        containerColor = Color(0xFFFFCC01),
        tonalElevation = 1.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navItems.forEach {
            NavigationBarItem(
                selected = currentRoute == it.route,
                onClick = {navController.navigate(it.route)},
                icon = {Icon(it.icon, contentDescription = null, tint = Color(0xFF3363AF))}
            )
        }
    }
}