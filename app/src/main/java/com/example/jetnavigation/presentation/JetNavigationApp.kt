package com.example.jetnavigation.presentation

import androidx.compose.runtime.Composable
import com.example.jetnavigation.presentation.navigation.AppNavHost
import com.example.jetnavigation.presentation.theme.JetNavigationTheme

@Composable
fun JetNavigationApp() {
    JetNavigationTheme {
        AppNavHost()
    }
}