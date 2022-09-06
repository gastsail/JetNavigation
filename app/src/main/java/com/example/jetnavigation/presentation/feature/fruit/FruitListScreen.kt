package com.example.jetnavigation.presentation.feature.fruit

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetnavigation.R
import com.example.jetnavigation.data.Fruit
import com.example.jetnavigation.data.LocalFruitRepository.Companion.fruitList
import com.example.jetnavigation.presentation.theme.GreenBackground
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
                Toast.makeText(
                    LocalContext.current,
                    "Error ${state.error}",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FruitList(fruits: List<Fruit>, onFruitClicked: (Fruit) -> Unit) {
    LazyColumn(Modifier.background(Color.White)) {

        stickyHeader {
            HeaderSection()
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
fun HeaderSection() {
    Column(Modifier.background(Color.White)) {
        WelcomeUserSection()
        InfoCardSection()
        WeeksBestSellerSection()
    }
}

@Composable
fun InfoCardSection() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .height(160.dp),
        shape = RoundedCornerShape(30.dp),
        backgroundColor = GreenBackground
    ) {
        Column(horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .width(100.dp)
                        .height(80.dp)
                        .padding(end = 16.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = 0.dp,
                    backgroundColor = Color.White.copy(alpha = 0.2f)
                ) {
                    Image(
                        modifier = Modifier
                            .size(35.dp)
                            .padding(8.dp),
                        contentScale = ContentScale.Fit,
                        painter = painterResource(id = R.drawable.fruitbowl),
                        contentDescription = "fruit_bowl",
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }

                Text(
                    modifier = Modifier.padding(end = 32.dp),
                    text = "We picked up a new portion of fresh fruits for you!",
                    fontFamily = FontFamily.Serif,
                    color = Color.White,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Button(
                    modifier = Modifier
                        .height(120.dp)
                        .width(120.dp)
                        .offset(x = 15.dp, y = (10).dp),
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White.copy(alpha = 0.6f),
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(bottom = 8.dp),
                    elevation = ButtonDefaults.elevation(defaultElevation = 0.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(35.dp),
                        imageVector = Icons.Outlined.ArrowForward,
                        contentDescription = "bestsellers"
                    )
                }

        }
    }
}

@Composable
fun WelcomeUserSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp, top = 32.dp, bottom = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f), text = "Hello, Elon!",
            style = MaterialTheme.typography.h5,
            color = GreenText,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif
        )
        Image(
            modifier = Modifier
                .size(35.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.elon),
            contentDescription = "profile_picture"
        )
    }
}

@Composable
fun WeeksBestSellerSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp, bottom = 16.dp, top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Week's bestsellers",
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp)
                .weight(1f),
            color = GreenText,
            fontFamily = FontFamily.Serif,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )

        Button(
            modifier = Modifier
                .width(35.dp)
                .height(35.dp),
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = GreenBackground,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowForward,
                contentDescription = "bestsellers"
            )
        }
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

@Preview(showBackground = true)
@Composable
private fun HeaderPreview() {
    HeaderSection()
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