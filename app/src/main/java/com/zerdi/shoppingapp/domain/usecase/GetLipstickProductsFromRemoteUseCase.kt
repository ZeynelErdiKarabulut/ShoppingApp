package com.zerdi.shoppingapp.domain.usecase

import com.zerdi.shoppingapp.common.Resource
import com.zerdi.shoppingapp.data.repository.ShoppingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetLipstickProductsFromRemoteUseCase @Inject constructor(val repository: ShoppingRepository) {
    suspend fun invoke() = flow {
        emit(Resource.Loading)
        try {
            val lipstickProducts = repository.getLipstickProducts()
            emit(Resource.Success(lipstickProducts))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)
}