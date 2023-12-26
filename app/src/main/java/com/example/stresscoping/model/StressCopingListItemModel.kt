package com.example.stresscoping.model

data class StressCopingListItemModel(
    val stressCoping: StressCopingModel?,
    val type: Type
) {
    enum class Type {
        Body, Footer
    }
}