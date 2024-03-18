import CategoriesAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stevenlopez.block2.p1.wundersale.R
import com.stevenlopez.block2.p1.wundersale.adapters.ItemAdapter
import com.stevenlopez.block2.p1.wundersale.data.model.Product
import com.stevenlopez.block2.p1.wundersale.data.model.ProductResponse
import com.stevenlopez.block2.p1.wundersale.data.Api
import com.stevenlopez.block2.p1.wundersale.data.model.Category
import com.stevenlopez.block2.p1.wundersale.data.RetrofitHelper
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

        // Retrieve the authentication token from SharedPreferences using RetrofitHelper
        val authToken = getAuthToken()
        fetchProducts(authToken)

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

                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {

            }
        })
    }

    private fun getAuthToken(): String {
        // Retrieve the authentication token from SharedPreferences using RetrofitHelper
        val context = requireContext().applicationContext
        RetrofitHelper.setSharedPreferences(context)
        val token = RetrofitHelper.sharedPreferences.getString("token", "")
        return token ?: "" // Return the token if available, otherwise return an empty string
    }
}
