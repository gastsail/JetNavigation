package com.example.jetnavigation.data

import com.example.jetnavigation.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalFruitRepository @Inject constructor() {

    suspend fun getAllFruits(): List<Fruit> = withContext(Dispatchers.IO) {
        delay(MAX_DELAY)
        fruitList
    }

    suspend fun getFruitById(id: Long): Fruit? = withContext(Dispatchers.IO) {
        delay(MAX_DELAY)
        fruitList.find { it.id == id }
    }

    companion object {
        private const val MAX_DELAY = 500L

        val fruitList = listOf(
            Fruit(
                id = 0,
                name = "Avocado",
                subtitle = "Super Tasty",
                description = "The avocado is a rather unique fruit. \n While most fruit consists primarily of carbohydrate, avocado is high in healthy fats.",
                price = 1,
                image = R.drawable.avocado
            ),
            Fruit(
                id = 1,
                name = "Banana",
                subtitle = "Ripe and Tasty",
                description = "Rich in potassium and vitamins B6 and C, bananas also contain essential amino acids, high amounts of carbohydrate and fiber, but low amounts of protein and fat.",
                price = 2,
                image = R.drawable.banana
            ),
            Fruit(
                id = 2,
                name = "Banana",
                subtitle = "Ripe and Tasty",
                description = "Rich in potassium and vitamins B6 and C, bananas also contain essential amino acids, high amounts of carbohydrate and fiber, but low amounts of protein and fat.",
                price = 2,
                image = R.drawable.banana
            ),
            Fruit(
                id = 3,
                name = "Banana",
                subtitle = "Ripe and Tasty",
                description = "Rich in potassium and vitamins B6 and C, bananas also contain essential amino acids, high amounts of carbohydrate and fiber, but low amounts of protein and fat.",
                price = 2,
                image = R.drawable.banana
            ),
            Fruit(
                id = 4,
                name = "Banana",
                subtitle = "Ripe and Tasty",
                description = "Rich in potassium and vitamins B6 and C, bananas also contain essential amino acids, high amounts of carbohydrate and fiber, but low amounts of protein and fat.",
                price = 2,
                image = R.drawable.banana
            ),
            Fruit(
                id = 5,
                name = "Banana",
                subtitle = "Ripe and Tasty",
                description = "Rich in potassium and vitamins B6 and C, bananas also contain essential amino acids, high amounts of carbohydrate and fiber, but low amounts of protein and fat.",
                price = 2,
                image = R.drawable.banana
            ),
            Fruit(
                id = 6,
                name = "Banana",
                subtitle = "Ripe and Tasty",
                description = "Rich in potassium and vitamins B6 and C, bananas also contain essential amino acids, high amounts of carbohydrate and fiber, but low amounts of protein and fat.",
                price = 2,
                image = R.drawable.banana
            ),
            Fruit(
                id = 7,
                name = "Banana",
                subtitle = "Ripe and Tasty",
                description = "Rich in potassium and vitamins B6 and C, bananas also contain essential amino acids, high amounts of carbohydrate and fiber, but low amounts of protein and fat.",
                price = 2,
                image = R.drawable.banana
            ),
            Fruit(
                id = 8,
                name = "Banana",
                subtitle = "Ripe and Tasty",
                description = "Rich in potassium and vitamins B6 and C, bananas also contain essential amino acids, high amounts of carbohydrate and fiber, but low amounts of protein and fat.",
                price = 2,
                image = R.drawable.banana
            ),
            Fruit(
                id = 9,
                name = "Banana",
                subtitle = "Ripe and Tasty",
                description = "Rich in potassium and vitamins B6 and C, bananas also contain essential amino acids, high amounts of carbohydrate and fiber, but low amounts of protein and fat.",
                price = 2,
                image = R.drawable.banana
            )
        )
    }
}