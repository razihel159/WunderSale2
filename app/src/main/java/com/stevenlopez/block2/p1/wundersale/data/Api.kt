package com.stevenlopez.block2.p1.wundersale.data

import com.stevenlopez.block2.p1.wundersale.data.model.ProductResponse
import com.stevenlopez.block2.p1.wundersale.data.model.LoginResponse
import com.stevenlopez.block2.p1.wundersale.data.model.Product
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path


interface Api {
    @FormUrlEncoded
    @POST("login")
    @Headers("Accept: application/json")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<LoginResponse>

    @GET("items")
    fun getItems(@Header("Authorization") token: String): Call<ProductResponse>
    suspend fun getProductById(@Path("productId") productId: Int): Product


    companion object{
        const val BASE_URL = "https://wundersale.shop/api/"
    }
}