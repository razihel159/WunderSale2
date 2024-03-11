package com.stevenlopez.block2.p1.wundersale.data

import com.stevenlopez.block2.p1.wundersale.data.model.Categories
import com.stevenlopez.block2.p1.wundersale.data.model.Item
import retrofit2.Call
import retrofit2.http.GET


interface Api {

    @GET("/dashboard")
    fun getList(): Call<List<Item>>

    companion object{
        const val BASE_URL = "https://wundersale.shop/api/"
    }
}