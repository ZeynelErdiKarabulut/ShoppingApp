package com.zerdi.shoppingapp.presentation.mainfeed.eyeliner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerdi.shoppingapp.common.Resource
import com.zerdi.shoppingapp.data.model.ProductFeatures
import com.zerdi.shoppingapp.domain.usecase.GetEyelinerProductsFromRemoteUseCase
import com.zerdi.shoppingapp.domain.usecase.InsertProductToLocalUseCase
import com.zerdi.shoppingapp.presentation.mainfeed.MainFeedEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EyelinerViewModel @Inject constructor(
    private val getEyelinerProductsUseCase: GetEyelinerProductsFromRemoteUseCase,
    private val insertProductToLocalUseCase: InsertProductToLocalUseCase
) :
    ViewModel() {

    private val _uiState = MutableStateFlow(EyelinerFeedUiState())
    val uiState: StateFlow<EyelinerFeedUiState> = _uiState.asStateFlow()

    fun handleEvent(event: MainFeedEvent) {
        when (event) {
            is MainFeedEvent.SaveMainToLocalDatabase -> {
                saveProductToLocalDb(event.product)
            }
            is MainFeedEvent.GetMain -> {
                getEyelinerProducts()
            }
            else -> {}
        }
    }

    private fun getEyelinerProducts() {
        viewModelScope.launch {
            getEyelinerProductsUseCase.invoke().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(eyelinerItems = resource.data as List<ProductFeatures>)
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(error = resource.message as String)
                        }
                    }
                    is Resource.Loading -> {
                        _uiState.update {
                            it.copy(isLoading = true)
                        }
                    }
                }
            }
        }
    }

    private fun saveProductToLocalDb(product: ProductFeatures) {
        viewModelScope.launch {
            insertProductToLocalUseCase.invoke(product).collect { }
        }
    }
}