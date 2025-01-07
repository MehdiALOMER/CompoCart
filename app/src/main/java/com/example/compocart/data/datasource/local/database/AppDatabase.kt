package com.example.compocart.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.compocart.data.local.converter.StringListConverter
import com.example.compocart.data.local.dao.ProductDao
import com.example.compocart.data.model.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}