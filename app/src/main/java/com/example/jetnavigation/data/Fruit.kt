package com.example.jetnavigation.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fruit(
    val id: Long,
    val name: String,
    val subtitle: String,
    val description: String,
    val price: Float,
    @DrawableRes val image: Int,
) : Parcelable