package com.example.jetnavigation.presentation.feature.fruit

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnavigation.R
import com.example.jetnavigation.data.Fruit
import com.example.jetnavigation.presentation.theme.GreenBackground
import com.example.jetnavigation.presentation.theme.GreenText
import java.text.DecimalFormat

@Composable
fun FruitDetailScreen(fruit: Fruit?, onNavigateBack: () -> Unit) {
    val safeFruitImage = fruit?.image ?: R.drawable.avocado
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy((-25).dp)
    ) {
        HeaderSection(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            fruitImage = safeFruitImage
        )
        FooterSection(fruit)
    }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .padding(32.dp)
    ) {
        TopBarNavigationSection(onNavigateBack)
    }
}


@Composable
private fun TopBarNavigationSection(onNavigateBack: () -> Unit) {
    Row(verticalAlignment = Alignment.Top) {
        Button(
            modifier = Modifier
                .size(35.dp),
            onClick = { onNavigateBack.invoke() },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White.copy(alpha = .3f),
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "bestsellers"
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .size(35.dp),
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White.copy(alpha = .3f),
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(8.dp)
        ) {
            Icon(
                modifier = Modifier.shadow(elevation = 0.dp),
                imageVector = Icons.Outlined.ShoppingCart,
                contentDescription = "bestsellers"
            )
        }
    }
}

@Composable
private fun HeaderSection(modifier: Modifier, fruitImage: Int) {
    Box(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = fruitImage),
            contentDescription = "fruit image",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun FooterSection(fruit: Fruit?) {

    var isFavorite by remember { mutableStateOf(false) }
    var itemQty by remember { mutableStateOf(-1) }

    if (fruit != null) {
        Card(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom
            ) {
                Row {
                    Column {
                        Text(
                            text = fruit.name, color = GreenText,
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = fruit.subtitle,
                            style = MaterialTheme.typography.subtitle1,
                            color = GreenText
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp),
                        onClick = { isFavorite = !isFavorite },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = GreenBackground,
                            contentColor = Color.White
                        ),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        Icon(
                            imageVector = when (isFavorite) {
                                true -> Icons.Filled.Favorite
                                false -> Icons.Outlined.FavoriteBorder
                            },
                            contentDescription = "bestsellers"
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(bottom = 24.dp, top = 16.dp, end = 35.dp),
                    text = fruit.description,
                    color = GreenText,
                    style = MaterialTheme.typography.body1
                )

                IncrementDecrementSection(fruit) { currentQty ->
                    itemQty = currentQty
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    onClick = { /*TODO*/ },
                    enabled = itemQty != 0,
                    colors = ButtonDefaults.buttonColors(backgroundColor = GreenBackground)
                ) {
                    Text(text = "Add to card", color = Color.White)
                }
            }
        }
    }
}

//TODO THE STATE OF THE INCREMENT DECREMENT SHOULD BE PROPAGATED TO THE TOP WITH LAMBDAS
@Composable
private fun IncrementDecrementSection(
    fruit: Fruit?,
    onQtyChanged: (Int) -> Unit
) {
    if (fruit != null) {

        var qty by remember { mutableStateOf(1) }
        var scaleValue by remember { mutableStateOf(0f) }
        val totalPrice by remember { mutableStateOf(fruit.price) }

        LaunchedEffect(qty) {
            if (qty > 0) {
                animate(
                    .5f, 1f, animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ) { value, _ -> scaleValue = value }
            }
        }

        Row(modifier = Modifier.padding(bottom = 32.dp), verticalAlignment = Alignment.CenterVertically) {
            OutlinedButton(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp),
                onClick = {
                    if (qty != 0) {
                        qty--
                        onQtyChanged.invoke(qty)
                    }
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.DarkGray
                ),
                contentPadding = PaddingValues(8.dp)
            ) {
                Icon(
                    imageVector = when (qty) {
                        1 -> Icons.Default.Delete
                        else -> Icons.Default.Remove
                    }, contentDescription = "add",
                    tint = when (qty) {
                        0 -> Color.Gray
                        else -> GreenText
                    }
                )
            }

            Text(
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp),
                text = "$qty",
                color = GreenText,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold
            )

            OutlinedButton(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp),
                onClick = {
                    qty++
                    onQtyChanged.invoke(qty)
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.DarkGray
                ),
                contentPadding = PaddingValues(8.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add", tint = GreenText)
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    modifier = Modifier.graphicsLayer {
                    scaleX = scaleValue
                    scaleY = scaleValue
                },
                    text = "$${DecimalFormat("0.00").format(totalPrice * qty)} ", color = GreenText,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "total price",
                    color = GreenText.copy(alpha = .8f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewFruitDetailScreen() {
    FruitDetailScreen(
        fruit = Fruit(
            id = 0,
            name = "Avocado",
            subtitle = "Super tasty",
            description = "The avocado is a rather unique fruit. While most fruit consists primarily of carbohydrate, avocado is high in healthy fats.",
            price = 0.99f,
            image = R.drawable.avocado
        )
    ) {}
}