package com.stevenlopez.block2.p1.wundersale.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stevenlopez.block2.p1.wundersale.R
import com.stevenlopez.block2.p1.wundersale.data.model.Product

class ItemAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = productList[position]
        Log.d("Image URL", currentItem.imageUrl) // Log image URL
        holder.textProductName.text = currentItem.name
        holder.textCategory.text = currentItem.category
        holder.textProductPrice.text = String.format("₱%.2f", currentItem.price)

        val imageUrl = "https://wundersale.shop/storage/${currentItem.imageUrl}"

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.upang_logo)
            .into(holder.imageProduct)
        Log.d("Image URL", imageUrl)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageProduct: ImageView = itemView.findViewById(R.id.imageProduct)
        val textProductName: TextView = itemView.findViewById(R.id.textProductName)
        val textCategory: TextView = itemView.findViewById(R.id.textCategory)
        val textProductPrice: TextView = itemView.findViewById(R.id.textProductPrice)
    }
}