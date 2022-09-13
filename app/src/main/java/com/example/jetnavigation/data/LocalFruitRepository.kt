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
        private const val MAX_DELAY = 1000L

        val fruitList = listOf(
            Fruit(
                id = 0,
                name = "Avocado",
                subtitle = "Super tasty",
                description = "The avocado is a rather unique fruit. /n While most fruit consists primarily of carbohydrate, avocado is high in healthy fats.",
                price = 0.99f,
                image = R.drawable.avocado
            ),
            Fruit(
                id = 1,
                name = "Banana",
                subtitle = "Super tasty",
                description = "Banana description",
                price = 0.99f,
                image = R.drawable.banana
            ),
            Fruit(
                id = 2,
                name = "Dragonfruit",
                subtitle = "Super tasty",
                description = "Dragonfruit description",
                price = 0.99f,
                image = R.drawable.dragonfruit
            ),
            Fruit(
                id = 3,
                name = "Grapefruit",
                subtitle = "Super tasty",
                description = "Grapefruit description",
                price = 0.99f,
                image = R.drawable.grapefruit
            ),
            Fruit(
                id = 4,
                name = "Lemon",
                subtitle = "Super tasty",
                description = "Lemon description",
                price = 0.99f,
                image = R.drawable.lemon
            ),
            Fruit(
                id = 5,
                name = "Pomegranade",
                subtitle = "Super tasty",
                description = "Pomegranade description",
                price = 0.99f,
                image = R.drawable.pomegranate
            ),
            Fruit(
                id = 6,
                name = "Strawberry",
                subtitle = "Super tasty",
                description = "Strawberry description",
                price = 0.99f,
                image = R.drawable.strawberry
            ),
            Fruit(
                id = 7,
                name = "Watermelon",
                subtitle = "Super tasty",
                description = "Watermelon description",
                price = 0.99f,
                image = R.drawable.watermelon
            )
        )
    }
}