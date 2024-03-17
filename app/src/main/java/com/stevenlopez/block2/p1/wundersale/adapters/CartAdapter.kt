import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stevenlopez.block2.p1.wundersale.R
import com.stevenlopez.block2.p1.wundersale.data.model.CartItem

class CartAdapter(private val cartItems: List<CartItem>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = cartItems[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemNameTextView: TextView = itemView.findViewById(R.id.item_title)
        private val itemPriceTextView: TextView = itemView.findViewById(R.id.item_price)
        private val itemQuantityTextView: TextView = itemView.findViewById(R.id.quantity_textview)
        private val itemImageView: ImageView = itemView.findViewById(R.id.item_image)
        private val minusButton: Button = itemView.findViewById(R.id.minus_button)
        private val plusButton: Button = itemView.findViewById(R.id.plus_button)

        fun bind(cartItem: CartItem) {
            val item = cartItem.item
            itemNameTextView.text = item.name
            itemPriceTextView.text = "₱" + item.price
            itemQuantityTextView.text = cartItem.quantity.toString()

            val imageUrl = "https://wundersale.shop/storage/${item.imageUrl}"

            Glide.with(itemView.context)
                .load(imageUrl)
                .placeholder(R.drawable.upang_logo)
                .into(itemImageView)

            minusButton.setOnClickListener {
                if (cartItem.quantity > 0) {
                    val updatedQuantity = cartItem.quantity - 1
                    cartItem.quantity = updatedQuantity
                    itemQuantityTextView.text = updatedQuantity.toString()
                }
            }

            plusButton.setOnClickListener {
                val updatedQuantity = cartItem.quantity + 1
                // Update the quantity of the current item
                cartItem.quantity = updatedQuantity
                // Update the quantity text view
                itemQuantityTextView.text = updatedQuantity.toString()
            }
        }
    }
}
