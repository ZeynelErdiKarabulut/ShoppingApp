package com.zerdi.shoppingapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zerdi.shoppingapp.data.model.ProductFeatures


@Database(
    entities = [ProductFeatures::class],
    version = 2,
    exportSchema = false
)

abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDAO
}