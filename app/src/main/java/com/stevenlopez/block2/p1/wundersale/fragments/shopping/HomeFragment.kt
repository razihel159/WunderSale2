package com.stevenlopez.block2.p1.wundersale.fragments.shopping

import CategoriesAdapter
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stevenlopez.block2.p1.wundersale.R
import com.stevenlopez.block2.p1.wundersale.adapters.ItemAdapter
import com.stevenlopez.block2.p1.wundersale.data.Api
import com.stevenlopez.block2.p1.wundersale.data.model.Product
import com.stevenlopez.block2.p1.wundersale.data.model.ProductResponse
import com.stevenlopez.block2.p1.wundersale.data.model.Category
import com.stevenlopez.block2.p1.wundersale.data.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private lateinit var recyclerViewProducts: RecyclerView
    private lateinit var recyclerViewCategories: RecyclerView
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter
    private val productList: MutableList<Product> = mutableListOf()
    private val categoryList: MutableList<Category> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerViewProducts = root.findViewById(R.id.products_list)
        recyclerViewProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        itemAdapter = ItemAdapter(requireContext(), productList)
        recyclerViewProducts.adapter = itemAdapter

        recyclerViewCategories = root.findViewById(R.id.category_list)
        recyclerViewCategories.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        categoriesAdapter = CategoriesAdapter(categoryList)
        recyclerViewCategories.adapter = categoriesAdapter

        // Retrieve the authentication token from SharedPreferences
        val authToken = getAuthToken(requireContext())
        if (authToken.isNullOrEmpty()) {
            Log.e("HomeFragment", "Authentication token is null or empty")
            // Handle the case where token is null or empty
        } else {
            fetchProducts(authToken)
        }

        return root
    }

    private fun fetchProducts(authToken: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://wundersale.shop/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(Api::class.java)
        val call = service.getItems("Bearer $authToken")

        call.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                if (response.isSuccessful) {
                    val productResponse = response.body()
                    productResponse?.let {
                        productList.clear()
                        productList.addAll(it.items)
                        itemAdapter.notifyDataSetChanged()

                        categoryList.clear()
                        categoryList.addAll(it.categories)
                        categoriesAdapter.notifyDataSetChanged()
                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("HomeFragment", "Failed to fetch products: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                // Handle failure
                Log.e("HomeFragment", "Failed to fetch products: ${t.message}")
            }
        })
    }

    private fun getAuthToken(context: Context): String? {
        return LoginResponse.getToken(context)
    }
}
