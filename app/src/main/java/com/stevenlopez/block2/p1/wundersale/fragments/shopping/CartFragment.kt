package com.stevenlopez.block2.p1.wundersale.fragments.shopping

import CartAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stevenlopez.block2.p1.wundersale.R
import com.stevenlopez.block2.p1.wundersale.data.Api
import com.stevenlopez.block2.p1.wundersale.data.model.CartItem
import com.stevenlopez.block2.p1.wundersale.data.model.CartResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CartFragment : Fragment() {

    private lateinit var cartAdapter: CartAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        recyclerView = view.findViewById(R.id.recycler_view_cart)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Assuming you have a method to retrieve cart items from Retrofit
        fetchCartItems()

        return view
    }

    private fun fetchCartItems() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://wundersale.shop/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(Api::class.java)
        val authToken = "C26wYxYlMQVo7skNRLm1kWhJ0nf5Xt4IkqziPLFyc2d7a21d"

        val call = service.getCart("Bearer $authToken")
        call.enqueue(object : Callback<CartResponse> {
            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                if (response.isSuccessful) {
                    val cartResponse = response.body()
                    cartResponse?.let {
                        // Pass the cart items to the adapter
                        cartAdapter = CartAdapter(it.cartItems) // Ensure it.cartItems is a List<CartItem>
                        recyclerView.adapter = cartAdapter
                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
