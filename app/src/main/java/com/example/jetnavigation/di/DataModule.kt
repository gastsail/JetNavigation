package com.example.jetnavigation.di

import com.example.jetnavigation.data.LocalFruitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    fun provideLocalFruitRepository() = LocalFruitRepository()
}