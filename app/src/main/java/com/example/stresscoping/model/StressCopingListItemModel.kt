package com.example.stresscoping.model

data class StressCopingListItemModel(
    val stressCoping: StressCopingModel?,
    val type: Type,
    var isVisibleCheckBox: Boolean = false,
    var isCheck: Boolean = false,
    var isVisibleEdit: Boolean = true,
    var isVisibleDelete: Boolean = true
) {
    enum class Type {
        Body, Footer
    }
}