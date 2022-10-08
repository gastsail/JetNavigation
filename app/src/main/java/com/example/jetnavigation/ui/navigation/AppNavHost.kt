package com.example.jetnavigation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.jetnavigation.presentation.FruitUiState
import com.example.jetnavigation.ui.navigation.FruitNavigation.FruitDetailNavigation
import com.example.jetnavigation.ui.navigation.FruitNavigation.FruitListNavigation

@Composable
fun AppNavHost(
    uiState: FruitUiState,
    startDestination: String = FruitListNavigation.route,
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        fruitGraph(
            uiState = uiState,
            onNavigateToFruitDetail = { fruit ->
                navController.navigate(FruitDetailNavigation.getDestination(fruit))
            },
            onNavigateBack = {
                navController.popBackStack()
            }
        )
    }
}