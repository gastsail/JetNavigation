package com.example.jetnavigation.presentation.feature.showfruits

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.jetnavigation.data.Fruit

@Composable
fun FruitDetailScreen(fruitViewModel: FruitViewModel) {
    val fruitUiState = fruitViewModel.fruitUiState.observeAsState()

    if(fruitUiState.value?.hasSelectedFruit() == true){
        FruitDetail(fruit = fruitUiState.value?.selectedFruit)
    }
}

@Composable
fun FruitDetail(fruit: Fruit?) {
    // TODO Add description text
    fruit?.let {
        Text(text = it.name)
    }
}