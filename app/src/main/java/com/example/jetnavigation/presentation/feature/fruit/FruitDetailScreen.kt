package com.example.jetnavigation.presentation.feature.fruit

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy((-25).dp)) {
        HeaderSection(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            fruitImage = safeFruitImage, onNavigateBack
        )
        FooterSection(fruit)
    }
}

/**
 * This is the code for the top bar, we need to overlap it with the image inside the header section
 *
 * Row {
Button(
modifier = Modifier
.width(35.dp)
.height(35.dp),
onClick = { onNavigateBack.invoke() },
shape = RoundedCornerShape(8.dp),
colors = ButtonDefaults.buttonColors(
backgroundColor = Color.White.copy(alpha = .8f),
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
.width(35.dp)
.height(35.dp),
onClick = { /*TODO*/ },
shape = RoundedCornerShape(8.dp),
colors = ButtonDefaults.buttonColors(
backgroundColor = Color.White.copy(alpha = .8f),
contentColor = Color.White
),
contentPadding = PaddingValues(0.dp)
) {
Icon(
imageVector = Icons.Outlined.ShoppingCart,
contentDescription = "bestsellers"
)
}
}
 */

@Composable
fun HeaderSection(modifier: Modifier, fruitImage: Int, onNavigateBack: () -> Unit) {
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
fun FooterSection(fruit: Fruit?) {
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
                            style = MaterialTheme.typography.body1,
                            color = GreenText
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
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
                            imageVector = Icons.Rounded.Favorite,
                            contentDescription = "bestsellers"
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(bottom = 16.dp, top = 16.dp),
                    text = fruit.description,
                    color = GreenText,
                    style = MaterialTheme.typography.body2
                )

                IncrementDecrementSection(fruit)

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    onClick = { /*TODO*/ },
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
fun IncrementDecrementSection(
    fruit: Fruit?
) {
    if (fruit != null) {
        var qty by remember { mutableStateOf(0) }
        val totalPrice by remember { mutableStateOf(fruit.price) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedButton(
                modifier = Modifier
                    .width(35.dp)
                    .height(35.dp),
                onClick = { if (qty != 0) qty-- },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.DarkGray
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "-", color = GreenText,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            Text(
                modifier = Modifier.padding(end = 8.dp, start = 8.dp),
                text = "$qty",
                color = GreenText,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold
            )

            OutlinedButton(
                modifier = Modifier
                    .width(35.dp)
                    .height(35.dp),
                onClick = { qty++ },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.DarkGray
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "+",
                    color = GreenText,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column {
                Text(
                    text = "$${DecimalFormat("0.00").format(totalPrice * qty)} ", color = GreenText,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "total price", color = GreenText.copy(alpha = .8f))
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
            description = "The avocado is a rather unique fruit. /n While most fruit consists primarily of carbohydrate, avocado is high in healthy fats.",
            price = 0.99f,
            image = R.drawable.avocado
        )
    ) {}
}