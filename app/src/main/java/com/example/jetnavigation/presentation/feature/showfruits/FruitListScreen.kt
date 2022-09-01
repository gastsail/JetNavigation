package com.example.jetnavigation.presentation.feature.showfruits

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetnavigation.data.Fruit
import com.example.jetnavigation.data.LocalFruitRepository.Companion.fruitList
import com.example.jetnavigation.presentation.theme.GreenText

@Composable
fun FruitListScreen(
    fruitViewModel: FruitViewModel,
    onFruitClick: (Fruit) -> Unit,
) {
    val fruits = fruitViewModel.fruitUiState.observeAsState()

    fruits.value?.let {
        FruitList(fruits = it.fruits, onFruitClick)
    }
}

@Composable
fun FruitList(fruits: List<Fruit>, onFruitClick: (Fruit) -> Unit) {
    LazyColumn {
        item {
            Header()
        }

        items(fruits) { fruit ->
            FruitItem(fruit = fruit, onFruitClick = { onFruitClick(fruit) })
        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Week's bestsellers",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(end = 8.dp),
            color = GreenText,
            fontFamily = FontFamily.Serif,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun FruitItem(fruit: Fruit, onFruitClick: (fruit: Fruit) -> Unit) {
    Row(
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp, top = 8.dp, bottom = 8.dp)
            .clickable {
                onFruitClick(fruit)
            }) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
        ) {
            Image(
                painter = painterResource(id = fruit.image), contentDescription = "Fruit image",
                contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize()
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = fruit.name,
                color = GreenText,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = fruit.subtitle,
                fontSize = 10.sp,
                color = GreenText
            )

            Text(
                text = "$${fruit.price}",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = GreenText,
                fontFamily = FontFamily.Serif
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FruitItemPreview() {
    FruitItem(fruitList[0]) {}
}

@Preview(showBackground = true)
@Composable
private fun FruitListPreview() {
    FruitList(fruitList) {}
}