package com.stevenlopez.block2.p1.wundersale.data

import com.stevenlopez.block2.p1.wundersale.data.model.ProductX
import retrofit2.http.GET


interface Api {

    @GET("products/{type}")
    suspend fun getProductsList(): ProductX

    companion object{
        const val BASE_URL = "https://dummyjson.com/"
    }
}