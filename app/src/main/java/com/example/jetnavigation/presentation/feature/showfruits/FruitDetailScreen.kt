package com.example.jetnavigation.presentation.feature.showfruits

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.jetnavigation.data.Fruit

@Composable
fun FruitDetailScreen(fruitViewModel: FruitViewModel) {
    val fruits = fruitViewModel.fruitUiState.observeAsState()

    fruits.value?.let {
        FruitDetail(fruit = it.fruits.firstOrNull())
    }
}

@Composable
fun FruitDetail(fruit: Fruit?) {
    fruit?.let {
        Text(text = it.name)
    }
}