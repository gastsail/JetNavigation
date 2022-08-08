package com.example.jetnavigation.data

import android.content.res.Resources
import androidx.annotation.DrawableRes
import com.example.jetnavigation.R

data class Fruit(
    val id: Int,
    val name: String,
    val subtitle: String,
    val description: String,
    val price: Int,
    @DrawableRes val image: Int
)

val fruitList = listOf(
    Fruit(
        id = 0,
        name = "Avocado",
        subtitle = "Super Tasty",
        description = "The avocado is a rather unique fruit. \n While most fruit consists primarily of carbohydrate, avocado is high in healthy fats.",
        price = 158,
        image = R.drawable.avocado
    ),
    Fruit(
        id = 1,
        name = "Banana",
        subtitle = "Ripe and Tasty",
        description = "Rich in potassium and vitamins B6 and C, bananas also contain essential amino acids, high amounts of carbohydrate and fiber, but low amounts of protein and fat.",
        price = 105,
        image = R.drawable.banana
    )
)

fun findFruit(id: Int) = fruitList.find { it.id == id }

