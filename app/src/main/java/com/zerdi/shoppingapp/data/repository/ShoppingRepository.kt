package com.zerdi.shoppingapp.data.repository

import com.zerdi.shoppingapp.common.Constants
import com.zerdi.shoppingapp.data.local.ProductDAO
import com.zerdi.shoppingapp.data.model.ProductFeatures
import com.zerdi.shoppingapp.data.remote.ProductAPI
import javax.inject.Inject

class ShoppingRepository @Inject constructor(
    private val remoteDataSource: ProductAPI,
    private val localDataSource: ProductDAO
) {

    suspend fun insertProduct(product: ProductFeatures) = localDataSource.insertProduct(product)

    suspend fun getLipstickProducts() = remoteDataSource.getProducts(Constants.LIPSTICK)

    suspend fun getEyelinerProducts() = remoteDataSource.getProducts(Constants.EYELINER)

    suspend fun getEyeshadowProducts() = remoteDataSource.getProducts(Constants.EYESHADOW)

    fun getAllProductsFromLocal() = localDataSource.getAllProducts()

    suspend fun deleteProduct(product: ProductFeatures) = localDataSource.deleteProduct(product)

}