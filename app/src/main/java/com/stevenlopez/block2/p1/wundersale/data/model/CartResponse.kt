package com.stevenlopez.block2.p1.wundersale.data.model

data class CartResponse(
    val message: String,
    val cartItems: List<CartItem>
)
