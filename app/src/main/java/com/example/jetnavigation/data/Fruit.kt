package com.example.jetnavigation.data

import androidx.annotation.DrawableRes

data class Fruit(
    val id: Long,
    val name: String,
    val subtitle: String,
    val description: String,
    val price: Int,
    @DrawableRes val image: Int,
)