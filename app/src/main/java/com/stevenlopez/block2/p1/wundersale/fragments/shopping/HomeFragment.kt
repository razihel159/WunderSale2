package com.stevenlopez.block2.p1.wundersale.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stevenlopez.block2.p1.wundersale.R
import com.stevenlopez.block2.p1.wundersale.adapters.ItemAdapter
import com.stevenlopez.block2.p1.wundersale.data.model.Product
import com.stevenlopez.block2.p1.wundersale.data.model.ProductResponse
import com.stevenlopez.block2.p1.wundersale.data.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: ItemAdapter
    private var productList: MutableList<Product> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize RecyclerView
        recyclerView = root.findViewById(R.id.products_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        itemAdapter = ItemAdapter(productList)
        recyclerView.adapter = itemAdapter

        // Fetch products using Retrofit
        fetchProducts()

        return root
    }

    private fun fetchProducts() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://wundersale.shop/api/") // Replace with your base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(Api::class.java)
        val call = service.getItems()

        call.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                if (response.isSuccessful) {
                    val products = response.body()?.items ?: emptyList()
                    productList.clear() // Clear the list before adding new items
                    productList.addAll(products)
                    itemAdapter.notifyDataSetChanged()
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
