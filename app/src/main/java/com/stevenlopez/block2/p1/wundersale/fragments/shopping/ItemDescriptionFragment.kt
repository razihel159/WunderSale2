package com.stevenlopez.block2.p1.wundersale.fragments.shopping



import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.stevenlopez.block2.p1.wundersale.R
import com.stevenlopez.block2.p1.wundersale.adapters.ItemDescriptionAdapter
import com.stevenlopez.block2.p1.wundersale.data.Api
import com.stevenlopez.block2.p1.wundersale.data.model.ProductResponse
import com.stevenlopez.block2.p1.wundersale.data.model.LoginResponse
import com.stevenlopez.block2.p1.wundersale.data.model.Product
import com.stevenlopez.block2.p1.wundersale.helper.NonScrollableLinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ItemDescriptionFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemDescriptionAdapter
    private var productIdToShow: Int = 0 // Default value
    private val cartItems: MutableList<Product> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_itemdescription, container, false)

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.item_Description)
        recyclerView.layoutManager = NonScrollableLinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        // Initialize adapter with this fragment as AddToCartListener
        adapter = ItemDescriptionAdapter(requireContext())
        recyclerView.adapter = adapter

        // Fetch product data
        fetchProductData()

        return view
    }

    private fun fetchProductData() {
        // Retrieve the authentication token from SharedPreferences
        val authToken = LoginResponse.getToken(requireContext())

        // Fetch product data with authentication token
        if (authToken.isNullOrEmpty()) {
            Log.e("ItemDescriptionFragment", "Authentication token is null or empty")
            // Handle the case where token is null or empty
        } else {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://wundersale.shop/api/") // Replace with your base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(Api::class.java)
            val call = service.getItems("Bearer $authToken")

            call.enqueue(object : Callback<ProductResponse> {
                override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                    if (response.isSuccessful) {
                        val productResponse = response.body()
                        val productList = productResponse?.items ?: emptyList()
                        if (productList.isNotEmpty()) {
                            // Update the adapter with the fetched product list
                            adapter.setProductList(productList)
                            // Set the productIdToShow to the first product's ID
                            productIdToShow = productList.first().id
                        } else {
                            // Handle case where product list is empty
                            Log.e("ItemDescriptionFragment", "Product list is empty")
                        }
                    } else {
                        // Handle unsuccessful response
                        Log.e("ItemDescriptionFragment", "Failed to fetch product list: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                    // Handle failure
                    Log.e("ItemDescriptionFragment", "Failed to fetch product list: ${t.message}")
                }
            })
        }
    }

}


