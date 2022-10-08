package com.example.jetnavigation.ui.navigation

import android.net.Uri
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.jetnavigation.data.Fruit
import com.example.jetnavigation.presentation.FruitUiState
import com.example.jetnavigation.ui.screens.FruitDetailScreen
import com.example.jetnavigation.ui.screens.FruitListScreen
import com.example.jetnavigation.ui.navigation.FruitNavigation.FruitDetailNavigation
import com.example.jetnavigation.ui.navigation.FruitNavigation.FruitDetailNavigation.FRUIT_NAV_ARG
import com.example.jetnavigation.ui.navigation.FruitNavigation.FruitListNavigation
import com.google.gson.Gson

sealed class FruitNavigation(val route: String) {
    object FruitListNavigation : FruitNavigation(route = "fruit_list")
    object FruitDetailNavigation : FruitNavigation(route = "fruit_detail") {
        const val FRUIT_NAV_ARG = "fruit"

        fun getDestination(fruit: Fruit): String {
            val jsonFruit = Uri.encode(Gson().toJson(fruit))
            return "$route/$jsonFruit"
        }
    }
}

fun NavGraphBuilder.fruitGraph(
    uiState: FruitUiState,
    onNavigateToFruitDetail: (Fruit) -> Unit,
    onNavigateBack: () -> Unit
) {
    composable(route = FruitListNavigation.route) {
        when(uiState) {
            is FruitUiState.Loading -> {
                Toast.makeText(LocalContext.current, "Loading", Toast.LENGTH_SHORT).show()
            }

            is FruitUiState.FruitListScreenUiState -> {
                FruitListScreen(
                    uiState = uiState,
                    onNavigateToFruitDetail = onNavigateToFruitDetail
                )
            }

            is FruitUiState.Error -> {
                Toast.makeText(
                    LocalContext.current,
                    "Error ${uiState.exception.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    composable(route = FruitDetailNavigation.route + "/{$FRUIT_NAV_ARG}",
        arguments = listOf(navArgument(FRUIT_NAV_ARG) {
            type = FruitNavType
        })
    ) { navBackStackEntry ->
        navBackStackEntry.arguments?.getParcelable<Fruit>(FRUIT_NAV_ARG)?.let { fruit ->
            FruitDetailScreen(fruit = fruit, onNavigateBack = onNavigateBack)
        }
    }
}