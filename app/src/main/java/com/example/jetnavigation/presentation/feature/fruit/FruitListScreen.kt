package com.example.jetnavigation.presentation.feature.fruit

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetnavigation.data.Fruit
import com.example.jetnavigation.data.LocalFruitRepository.Companion.fruitList
import com.example.jetnavigation.presentation.theme.GreenText

@Composable
fun FruitListScreen(
    viewModel: FruitViewModel = hiltViewModel(),
    onNavigateToFruitDetail: (Fruit) -> Unit,
) {
    val fruitUiState = viewModel.fruitUiState.observeAsState()

    fruitUiState.value?.let { state ->
        when (state) {
            FruitUiState.Loading -> {
                Toast.makeText(LocalContext.current, "Loading", Toast.LENGTH_SHORT).show()
            }
            is FruitUiState.FruitUiListSuccess -> {
                FruitList(
                    fruits = state.fruitList,
                    onFruitClicked = onNavigateToFruitDetail
                )
            }
            is FruitUiState.Error -> {
                Toast.makeText(LocalContext.current,
                    "Error ${state.error}",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}

@Composable
fun FruitList(fruits: List<Fruit>, onFruitClicked: (Fruit) -> Unit) {
    LazyColumn {
        item {
            Header()
        }

        items(fruits) { fruit ->
            FruitItem(
                fruit = fruit,
                onFruitClicked = { onFruitClicked(fruit) }
            )
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
private fun FruitItem(fruit: Fruit, onFruitClicked: (fruit: Fruit) -> Unit) {
    Row(
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp, top = 8.dp, bottom = 8.dp)
            .clickable {
                onFruitClicked(fruit)
            }) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
        ) {
            Image(
                painter = painterResource(id = fruit.image),
                contentDescription = "Fruit image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
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

@Preview(name = "background", showBackground = true)
@Preview(name = "phone", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
@Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
@Preview(name = "foldable", device = "spec:shape=Normal,width=673,height=841,unit=dp,dpi=480")
@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
@Composable
private fun FruitListPreview() {
    FruitList(fruitList) {}
}