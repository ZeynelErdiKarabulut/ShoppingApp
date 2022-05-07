package com.zerdi.shoppingapp.data.remote

import com.zerdi.shoppingapp.data.model.ProductFeatures
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductAPI {
    @GET("api/v1/products.json")
    suspend fun getProducts(
        @Query("product_type")
        categoryName: String
    ): List<ProductFeatures>
}