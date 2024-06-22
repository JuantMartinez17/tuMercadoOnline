package com.example.parcial2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial2.R
import com.example.parcial2.data.Product
import com.example.parcial2.viewModel.ProductListViewModel

class ProductListAdapter (private val productList: List<Product>): RecyclerView.Adapter<ProductListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewModel = ProductListViewModel()
        return ProductListViewHolder(layoutInflater.inflate(R.layout.product_item, parent, false), viewModel, parent.context)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val item = productList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

}