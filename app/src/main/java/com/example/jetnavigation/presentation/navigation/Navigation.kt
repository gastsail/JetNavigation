package com.example.jetnavigation.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetnavigation.presentation.feature.showfruits.FruitDetailScreen
import com.example.jetnavigation.presentation.feature.showfruits.FruitListScreen
import com.example.jetnavigation.presentation.feature.showfruits.FruitViewModel

sealed class Screen(val route: String) {
    object FruitListScreen : Screen(route = "fruit")
    object FruitDetailScreen : Screen(route = "fruit_detail_screen")
}

@Composable
fun Navigation(fruitViewModel: FruitViewModel) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.FruitListScreen.route) {
        composable(route = Screen.FruitListScreen.route) {
            FruitListScreen(fruitViewModel, onFruitClick = { fruit ->
                fruitViewModel.findFruitById(fruit.id)
                navController.navigate(Screen.FruitDetailScreen.route + "/${fruit.id}")
            })
        }

        composable(route = Screen.FruitDetailScreen.route + "/{fruitId}",
            arguments = listOf(navArgument("fruitId") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("fruitId")?.toLong()?.let {
                FruitDetailScreen(fruitViewModel)
            }
        }
    }
}