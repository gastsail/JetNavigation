package com.example.jetnavigation.presentation.navigation

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.jetnavigation.data.Fruit
import com.example.jetnavigation.presentation.feature.fruit.FruitDetailScreen
import com.example.jetnavigation.presentation.feature.fruit.FruitListScreen
import com.example.jetnavigation.presentation.navigation.FruitNavigation.FruitDetailNavigation
import com.example.jetnavigation.presentation.navigation.FruitNavigation.FruitDetailNavigation.FRUIT_NAV_ARG
import com.example.jetnavigation.presentation.navigation.FruitNavigation.FruitListNavigation
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
    onNavigateToFruitDetail: (Fruit) -> Unit,
    onNavigateBack: () -> Unit
) {
    composable(route = FruitListNavigation.route) {
        FruitListScreen(
            onNavigateToFruitDetail = onNavigateToFruitDetail
        )
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