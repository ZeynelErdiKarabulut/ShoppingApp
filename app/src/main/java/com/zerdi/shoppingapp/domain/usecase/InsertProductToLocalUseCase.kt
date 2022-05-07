package com.zerdi.shoppingapp.domain.usecase

import com.zerdi.shoppingapp.common.Resource
import com.zerdi.shoppingapp.data.model.ProductFeatures
import com.zerdi.shoppingapp.data.repository.ShoppingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class InsertProductToLocalUseCase @Inject constructor(var repository: ShoppingRepository) {

    suspend fun invoke(product: ProductFeatures) = flow {
        emit(Resource.Loading)
        try {
            val insertProduct = repository.insertProduct(product)
            emit(Resource.Success(insertProduct))
        } catch (e: Exception) {
            println(e.localizedMessage)
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)
}