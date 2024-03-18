package com.stevenlopez.block2.p1.wundersale.data.model

import com.google.gson.annotations.SerializedName

data class Item(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    @SerializedName("image") val imageUrl: String,
    val quantity: Int,

)