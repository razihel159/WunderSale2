package com.stevenlopez.block2.p1.wundersale.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stevenlopez.block2.p1.wundersale.Activity.ShoppingActivity
import com.stevenlopez.block2.p1.wundersale.R
import com.stevenlopez.block2.p1.wundersale.data.model.Product

class ItemDescriptionAdapter(private val context: Context) :
    RecyclerView.Adapter<ItemDescriptionAdapter.ItemDescriptionViewHolder>() {

    private val cartAdapterList: MutableList<Product> = mutableListOf()

    private var productList: List<Product> = emptyList()

    inner class ItemDescriptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.item_image)
        val nameTextView: TextView = itemView.findViewById(R.id.item_name)
        val descriptionTextView: TextView = itemView.findViewById(R.id.item_description)
        val priceTextView: TextView = itemView.findViewById(R.id.item_price)
        val buyNowButton: Button = itemView.findViewById(R.id.buy_now)
        val addToCartButton: Button = itemView.findViewById(R.id.add_to_cart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDescriptionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_description, parent, false)
        return ItemDescriptionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemDescriptionViewHolder, position: Int) {
        val currentItem = productList[position]

        // Bind data to views
        Glide.with(context)
            .load("https://wundersale.shop/storage/${currentItem.imageUrl}") // Load image from URL
            .into(holder.imageView) // Set image into ImageView

        holder.nameTextView.text = currentItem.name
        holder.descriptionTextView.text = currentItem.description
        holder.priceTextView.text = String.format("₱%.2f", currentItem.price)

        // Set click listeners if needed
        holder.buyNowButton.setOnClickListener {
            Toast.makeText(context, "Item bought successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, ShoppingActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            context.startActivity(intent)
        }

        holder.addToCartButton.setOnClickListener {
            Toast.makeText(context, "Item added to cart", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, ShoppingActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setProductList(products: List<Product>) {
        productList = products
        notifyDataSetChanged()
    }
}

