package com.stevenlopez.block2.p1.wundersale.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    val id: Int,
    val name: String,
    val category: String,
    val price: Double,
    @SerializedName("image") val imageUrl: String
)
