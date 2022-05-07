package com.zerdi.shoppingapp.data.di

import android.content.Context
import androidx.room.Room
import com.zerdi.shoppingapp.data.local.ProductDAO
import com.zerdi.shoppingapp.data.local.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ProductDatabase =
        Room.databaseBuilder(context, ProductDatabase::class.java, "products_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideDao(productDatabase: ProductDatabase): ProductDAO {
        return productDatabase.productDao()
    }
}