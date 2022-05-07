package com.zerdi.shoppingapp.presentation.basketfeed

import com.zerdi.shoppingapp.data.model.ProductFeatures

sealed class BasketFeedEvent {
    data class DeleteProduct(val product: ProductFeatures) : BasketFeedEvent()
    data class SaveProduct(val product: ProductFeatures) : BasketFeedEvent()
    object GetProductsFromLocal : BasketFeedEvent()
}
