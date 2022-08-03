package com.example.jetnavigation.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.jetnavigation.data.findFruit

@Composable
fun FruitDetailScreen(navController: NavController, id: Int) {
    val fruit = findFruit(id)
    if(fruit != null) {
        Text(text = fruit.name)
    } else {
        navController.popBackStack()
    }
}