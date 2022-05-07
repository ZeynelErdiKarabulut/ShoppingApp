package com.zerdi.shoppingapp.presentation.mainfeed.eyeliner

import com.zerdi.shoppingapp.data.model.ProductFeatures

data class EyelinerFeedUiState(
    val error: String? = null,
    val isLoading: Boolean = false,
    val eyelinerItems : List<ProductFeatures>? = null
)
