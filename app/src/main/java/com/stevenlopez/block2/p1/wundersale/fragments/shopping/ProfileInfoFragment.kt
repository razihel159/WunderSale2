import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.stevenlopez.block2.p1.wundersale.R
import com.stevenlopez.block2.p1.wundersale.data.Api
import com.stevenlopez.block2.p1.wundersale.data.RetrofitHelper
import com.stevenlopez.block2.p1.wundersale.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileInfoFragment : Fragment() {
    private lateinit var rootView: View
    private val userID = 3
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_profileinfo, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchUserData()
    }

    private fun fetchUserData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://wundersale.shop/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(Api::class.java)
        val authToken = "C26wYxYlMQVo7skNRLm1kWhJ0nf5Xt4IkqziPLFyc2d7a21d"
        val headers = HashMap<String, String>()
        headers["Authorization"] = "Bearer $authToken"
        val call = service.getUser(userID)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    user?.let { updateUserDetails(it) }
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                // Handle failure
            }
        })
    }

    private fun updateUserDetails(user: User) {
        requireActivity().runOnUiThread {
            rootView.findViewById<TextView>(R.id.text_name)?.text = user?.name
            rootView.findViewById<TextView>(R.id.text_email)?.text = user?.email
            rootView.findViewById<TextView>(R.id.text_stunum)?.text = user?.student_number
        }
    }
}