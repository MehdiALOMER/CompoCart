package com.example.compocart.di

import android.content.Context
import androidx.room.Room
import com.example.compocart.data.datasource.local.dao.AuthTokenDao
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
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "compocart.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideProductDao(database: AppDatabase): ProductDao = database.productDao()

    @Provides
    @Singleton
    fun provideAuthTokenDao(database: AppDatabase): AuthTokenDao {
        return database.authTokenDao()
    }
}