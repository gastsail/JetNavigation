package com.example.jetnavigation.di

import androidx.compose.runtime.Composable
import com.example.jetnavigation.ui.navigation.AppNavHost
import com.example.jetnavigation.ui.theme.JetNavigationTheme

@Composable
fun JetNavigationApp() {
    JetNavigationTheme {
        AppNavHost()
    }
}