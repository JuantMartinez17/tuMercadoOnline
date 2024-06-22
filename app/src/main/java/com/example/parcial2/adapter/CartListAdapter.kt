package com.example.parcial2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial2.data.Product
import com.example.parcial2.databinding.CheckoutItemBinding

class CartListAdapter(private val cartItems: List<Product>) : RecyclerView.Adapter<CartListAdapter.CartItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val binding = CheckoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val product = cartItems[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    inner class CartItemViewHolder(private val binding:  CheckoutItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.tvProductName.text = product.name
            binding.tvProductPrice.text = "$ ${product.price}"
        }
    }
}