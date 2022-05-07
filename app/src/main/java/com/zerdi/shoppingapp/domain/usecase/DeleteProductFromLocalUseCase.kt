package com.zerdi.shoppingapp.domain.usecase

import com.zerdi.shoppingapp.common.Resource
import com.zerdi.shoppingapp.data.model.ProductFeatures
import com.zerdi.shoppingapp.data.repository.ShoppingRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteProductFromLocalUseCase @Inject constructor(val repository: ShoppingRepository) {

    suspend fun invoke(product: ProductFeatures) = flow {
        emit(Resource.Loading)
        try {
            val deleteProduct = repository.deleteProduct(product)
            emit(Resource.Success(deleteProduct))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }
}