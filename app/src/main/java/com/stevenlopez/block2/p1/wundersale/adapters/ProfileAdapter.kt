import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stevenlopez.block2.p1.wundersale.R
import com.stevenlopez.block2.p1.wundersale.data.model.ProfileResponse

class ProfileAdapter(private var profileList: List<ProfileResponse>) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_profile, parent, false)
        return ProfileViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val currentItem = profileList[position].user
        holder.textUserName.text = currentItem.name
        holder.textUserEmail.text = currentItem.email
        holder.textUserStudentNumber.text = currentItem.student_number
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textUserName: TextView = itemView.findViewById(R.id.text_user_name)
        val textUserEmail: TextView = itemView.findViewById(R.id.text_user_email)
        val textUserStudentNumber: TextView = itemView.findViewById(R.id.text_user_student_number)
    }

    fun setData(newProfileList: List<ProfileResponse>) {
        profileList = newProfileList
        notifyDataSetChanged()
    }
}