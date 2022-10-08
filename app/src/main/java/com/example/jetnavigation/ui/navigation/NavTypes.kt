package com.example.jetnavigation.ui.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.example.jetnavigation.data.Fruit
import com.example.jetnavigation.ui.navigation.FruitNavigation.FruitDetailNavigation.FRUIT_NAV_ARG
import com.google.gson.Gson

val FruitNavType: NavType<Fruit> = object : NavType<Fruit>(isNullableAllowed = false){
    override val name = FRUIT_NAV_ARG

    override fun get(bundle: Bundle, key: String): Fruit? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Fruit {
        return Gson().fromJson(value, Fruit::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Fruit) {
        bundle.putParcelable(key, value)
    }
}