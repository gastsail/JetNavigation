package com.example.jetnavigation.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.jetnavigation.presentation.feature.showfruits.FruitViewModel
import com.example.jetnavigation.presentation.navigation.Navigation
import com.example.jetnavigation.presentation.theme.JetNavigationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val fruitViewModel: FruitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetNavigationTheme {
                Navigation(fruitViewModel)
            }
        }
    }
}