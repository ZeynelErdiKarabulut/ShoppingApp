package com.zerdi.shoppingapp.presentation.basketfeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerdi.shoppingapp.common.Resource
import com.zerdi.shoppingapp.data.model.ProductFeatures
import com.zerdi.shoppingapp.domain.usecase.DeleteProductFromLocalUseCase
import com.zerdi.shoppingapp.domain.usecase.GetAllProductsFromLocalUseCase
import com.zerdi.shoppingapp.domain.usecase.InsertProductToLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val getAllProductsLocalUseCase: GetAllProductsFromLocalUseCase,
    private val deleteProductFromLocalUseCase: DeleteProductFromLocalUseCase,
    private val insertProductUseCase: InsertProductToLocalUseCase
) :
    ViewModel() {

    private val uiState = MutableStateFlow(BasketFeedUiState())
    val _uiState: StateFlow<BasketFeedUiState> = uiState.asStateFlow()

    private fun getAllProducts() {
        viewModelScope.launch {
            getAllProductsLocalUseCase.invoke().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        uiState.update {
                            it.copy(basketFeedItems = resource.data as List<ProductFeatures>)
                        }
                    }
                    is Resource.Error -> {
                        uiState.update {
                            it.copy(error = resource.message.toString())
                        }
                    }
                    is Resource.Loading -> {
                        uiState.update {
                            it.copy(isLoading = true)
                        }
                    }
                }
            }
        }
    }

    fun handleEvent(event: BasketFeedEvent) {
        when (event) {
            is BasketFeedEvent.DeleteProduct -> {
                viewModelScope.launch {
                    deleteProductFromLocalUseCase.invoke(event.product).collect { }
                }
            }
            is BasketFeedEvent.SaveProduct -> {
                viewModelScope.launch {
                    insertProductUseCase.invoke(event.product).collect { }
                }
            }
            is BasketFeedEvent.GetProductsFromLocal -> {
                getAllProducts()
            }
        }
    }
}