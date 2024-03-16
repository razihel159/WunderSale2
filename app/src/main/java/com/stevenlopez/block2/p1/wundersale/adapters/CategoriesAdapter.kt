import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stevenlopez.block2.p1.wundersale.R
import com.stevenlopez.block2.p1.wundersale.data.model.Category

class CategoriesAdapter(private val categories: List<Category>) :
    RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_categories, parent, false)
        return CategoryViewHolder(view)

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val listButton = categories[position]
        holder.categoryNameTextView.text = listButton.name
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    class CategoryViewHolder(listView: View) : RecyclerView.ViewHolder(listView) {
        val categoryNameTextView: TextView =
            itemView.findViewById(R.id.categoryTextView)
    }
}