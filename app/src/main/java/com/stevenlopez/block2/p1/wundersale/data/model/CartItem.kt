package com.stevenlopez.block2.p1.wundersale.data.model

import com.google.gson.annotations.SerializedName

data class CartItem(
    val id: Int,
    val userId: Int,
    val itemId: Int,
    var quantity: Int,
    val totalPrice: String,
    val createdAt: String,
    val updatedAt: String,
    val item: Item
)
