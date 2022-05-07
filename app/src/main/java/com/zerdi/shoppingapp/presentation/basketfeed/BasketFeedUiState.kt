package com.zerdi.shoppingapp.presentation.basketfeed

import com.zerdi.shoppingapp.data.model.ProductFeatures

data class BasketFeedUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val basketFeedItems: List<ProductFeatures>? = null
)