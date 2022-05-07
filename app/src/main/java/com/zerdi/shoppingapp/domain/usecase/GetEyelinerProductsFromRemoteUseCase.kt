package com.zerdi.shoppingapp.domain.usecase

import com.zerdi.shoppingapp.common.Resource
import com.zerdi.shoppingapp.data.repository.ShoppingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetEyelinerProductsFromRemoteUseCase @Inject constructor(var repository: ShoppingRepository) {

    suspend fun invoke() = flow {
        emit(Resource.Loading)
        try {
            val eyelinerProducts = repository.getEyelinerProducts()
            emit(Resource.Success(eyelinerProducts))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }

    }.flowOn(Dispatchers.IO)

}