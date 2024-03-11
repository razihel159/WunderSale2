package com.stevenlopez.block2.p1.wundersale.data.model

data class Item(
    val category_id: Int,
    val created_at: String,
    val description: String,
    val id: Int,
    val image: String,
    val name: String,
    val price: String,
    val quantity: Int,
    val updated_at: String,
    val user_id: Int
)