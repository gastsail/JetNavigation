package com.example.jetnavigation.presentation.feature.fruit

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.jetnavigation.data.Fruit

@Composable
fun FruitDetailScreen(fruit: Fruit?) {
    // TODO Add description text
    fruit?.let {
        Text(text = it.name)
    }
}