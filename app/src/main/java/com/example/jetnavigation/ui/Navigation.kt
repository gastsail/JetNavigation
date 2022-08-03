package com.example.jetnavigation.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object Master : Screen(route = "master")
    object Detail : Screen(route = "detail")
}

data class Fruit(val name: String)

val fruitList = listOf(Fruit("Orange"), Fruit("Pear"), Fruit("Banana"), Fruit("Grape"))

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Master.route) {
        composable(Screen.Master.route) {
            MasterScreen(navController = navController)
        }
        composable(Screen.Detail.route) {
            DetailScreen(navController, fruitList)
        }
    }
}

@Composable
private fun MasterScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate(Screen.Detail.route) }) {
            Text(text = "Go to details page")
        }
    }
}

@Composable
private fun DetailScreen(navController: NavController, fruitList: List<Fruit>) {
    LazyColumn {
        items(fruitList) {
            DetailItem(fruit = it)
        }
    }
}

@Composable
private fun DetailItem(fruit: Fruit) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        val context = LocalContext.current
        Text(modifier = Modifier
            .padding(start = 4.dp)
            .clickable {
                Toast
                    .makeText(context,"Clicked: ${fruit.name}", Toast.LENGTH_SHORT)
                    .show()
            },
            text = fruit.name)
        Divider()
    }
}

@Preview(showBackground = true)
@Composable
private fun MasterPreview() {
    MasterScreen(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
private fun DetailPreview() {
    DetailItem(Fruit("Orange"))
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    DetailScreen(navController = rememberNavController(), fruitList)
}