package com.example.parcial2.adapter

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial2.data.Product
import com.example.parcial2.databinding.ProductItemBinding
import com.example.parcial2.viewModel.ProductListViewModel

class ProductListViewHolder(view: View, private val viewModel: ProductListViewModel, private val context: Context): RecyclerView.ViewHolder(view) {
    private val binding = ProductItemBinding.bind(view)

    fun bind (product: Product){
        binding.tvProductName.text = product.name
        binding.tvProductPrice.text = "$" + product.price.toString()
        binding.fabAddProduct.setOnClickListener {
            viewModel.addProductToCart(product)
            Toast.makeText(context, "${product.name} añadido al carrito", Toast.LENGTH_SHORT).show()
        }
    }
}