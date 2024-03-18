package com.stevenlopez.block2.p1.wundersale.data

import android.content.Context
import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    lateinit var sharedPreferences: SharedPreferences
    private lateinit var instance: Api

    fun setSharedPreferences(context: Context) {
        // Assuming you have a way to get SharedPreferences from the context
        sharedPreferences = context.getSharedPreferences("YourSharedPrefsName", Context.MODE_PRIVATE)
    }

    fun createService(serviceClass: Class<Api>): Api {
        // Get the token from SharedPreferences
        val token = sharedPreferences.getString("token", "")

        // Create an OkHttpClient with the auth interceptor
        val client = OkHttpClient.Builder()
            .addInterceptor(createAuthInterceptor(token.orEmpty()))
            .build()

        // Create Retrofit instance
        instance = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(serviceClass)

        return instance
    }

    private fun createAuthInterceptor(token: String): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Authorization", "Bearer $token")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }
}
