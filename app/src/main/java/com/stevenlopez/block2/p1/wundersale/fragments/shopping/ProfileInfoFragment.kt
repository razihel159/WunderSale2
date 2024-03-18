
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stevenlopez.block2.p1.wundersale.R
import com.stevenlopez.block2.p1.wundersale.data.Api
import com.stevenlopez.block2.p1.wundersale.data.model.LoginResponse
import com.stevenlopez.block2.p1.wundersale.data.model.ProfileResponse
import com.stevenlopez.block2.p1.wundersale.fragments.shopping.ProfileFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileInfoFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var profileAdapter: ProfileAdapter
    private var profileList: List<ProfileResponse> = emptyList()
    val authToken = "C26wYxYlMQVo7skNRLm1kWhJ0nf5Xt4IkqziPLFyc2d7a21d"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profileinfo, container, false)

        recyclerView = view.findViewById(R.id.myProfile)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        profileAdapter = ProfileAdapter(profileList)
        recyclerView.adapter = profileAdapter

        fetchData()

        return view
    }

    private fun fetchData() {
        val authToken = LoginResponse.getToken(requireContext()) // Retrieve token from SharedPreferences

        authToken?.let { token ->
            val retrofit = Retrofit.Builder()
                .baseUrl("https://wundersale.shop/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(Api::class.java)
            val call = service.getUser("Bearer $token") // Include token in the request header

            call.enqueue(object : Callback<ProfileResponse> {
                override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            profileList = listOf(it) // Assuming your API returns a single profile
                            profileAdapter.setData(profileList)
                        }
                    } else {
                        // Handle unsuccessful response
                    }
                }

                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    // Handle failure
                }
            })
        } ?: run {
            // Token is null, handle this scenario
        }
    }
    fun updateProfileList(newProfileList: List<ProfileResponse>) {
        profileList = newProfileList
        profileAdapter.setData(profileList)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backButton = view.findViewById<Button>(R.id.BackButton)

        backButton.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(
                R.id.shoppingHostFragment,
                ProfileFragment()
            )
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}
