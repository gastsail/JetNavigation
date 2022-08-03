package com.example.jetnavigation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetnavigation.data.findFruit
import com.example.jetnavigation.data.fruitList
import com.example.jetnavigation.ui.screens.FruitDetailScreen
import com.example.jetnavigation.ui.screens.FruitScreen
import com.example.jetnavigation.ui.screens.MasterScreen

sealed class Screen(val route: String) {
    object MasterScreen : Screen(route = "master")
    object FruitScreen : Screen(route = "fruit")
    object FruitDetailScreen : Screen(route = "fruit_detail_screen")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MasterScreen.route) {
        composable(Screen.MasterScreen.route) {
            MasterScreen(navController = navController)
        }
        composable(route = Screen.FruitScreen.route) {
            FruitScreen(navController, fruitList)
        }

        composable(route = Screen.FruitDetailScreen.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("id")?.toInt()?.let {
                FruitDetailScreen(navController = navController,
                    id = it
                )
            }
        }
    }
}