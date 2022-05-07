package com.zerdi.shoppingapp.presentation.mainfeed

import com.zerdi.shoppingapp.data.model.ProductFeatures


sealed class MainFeedEvent {
    data class SaveMainToLocalDatabase(val product: ProductFeatures) : MainFeedEvent()
    object GetMain : MainFeedEvent()
}
