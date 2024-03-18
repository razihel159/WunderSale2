package com.stevenlopez.block2.p1.wundersale.helper

import com.stevenlopez.block2.p1.wundersale.data.model.Product

interface CartManager {
    fun addToCart(product: Product)
}