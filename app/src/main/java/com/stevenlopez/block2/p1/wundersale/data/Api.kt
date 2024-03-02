package com.stevenlopez.block2.p1.wundersale.data

import com.stevenlopez.block2.p1.wundersale.data.model.ProductX
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("products/{type}")
    suspend fun getProductsList(): Response<ProductX>

    companion object{
        const val BASE_URL = "https://dummyjson.com/"
    }
}