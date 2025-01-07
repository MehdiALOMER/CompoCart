package com.example.compocart.di

import android.content.Context
import androidx.room.Room
import com.example.compocart.data.local.database.AppDatabase
import com.example.compocart.data.local.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "compocart_db").build()

    @Provides
    fun provideProductDao(database: AppDatabase): ProductDao = database.productDao()
}