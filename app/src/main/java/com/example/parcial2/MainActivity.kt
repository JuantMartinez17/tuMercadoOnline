package com.example.parcial2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parcial2.adapter.ProductListAdapter
import com.example.parcial2.data.Product
import com.example.parcial2.data.ProductList
import com.example.parcial2.databinding.ActivityMainBinding
import com.example.parcial2.databinding.DialogTaskBinding
import com.example.parcial2.viewModel.ProductListViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productListViewModel: ProductListViewModel
    private lateinit var dialogProductBinding: DialogTaskBinding
    private lateinit var dialogCartBinding: DialogTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialogProductBinding = DialogTaskBinding.inflate(layoutInflater)
        dialogCartBinding = DialogTaskBinding.inflate(layoutInflater)

        productListViewModel = ViewModelProvider(this).get(ProductListViewModel::class.java)
        productListViewModel.productList.observe(this) { _ ->
            initRecycler(ProductList.listOfProducts)
        }

        binding.fabCart.setOnClickListener {
            showAlertDialog()
        }
    }

    private fun showAlertDialog() {
        productListViewModel.cartItems.observe(this) {
            dialogCartBinding.tvTotalPrice.text = "Total: $" + productListViewModel.getTotalPrice()
        }

        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setView(dialogCartBinding.root)
        val alertDialog = alertDialogBuilder.create()

        dialogCartBinding.btnCheckout.setOnClickListener {
            val cartItems = productListViewModel.getCartItemsLiveData().value
            if (cartItems != null) {
                navigateToCheckoutActivity(cartItems)
                alertDialog.dismiss()
            } else {
                Toast.makeText(this, "No hay productos en el carrito", Toast.LENGTH_SHORT).show()
            }
        }

        alertDialog.show()
    }

    private fun navigateToCheckoutActivity(cartItems: List<Product>) {
        val intent = Intent(this, CheckoutActivity::class.java)
        intent.putParcelableArrayListExtra("cartItems", ArrayList(cartItems))
        startActivity(intent)
    }

    private fun initRecycler(productList: List<Product>) {
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        binding.rvProducts.adapter = ProductListAdapter(productList)
    }
}