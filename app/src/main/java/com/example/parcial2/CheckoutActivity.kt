package com.example.parcial2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parcial2.adapter.CartListAdapter
import com.example.parcial2.data.Product
import com.example.parcial2.databinding.ActivityCheckoutBinding

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var cartListAdapter: CartListAdapter
    private lateinit var cartItems: List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        cartItems = intent.getParcelableArrayListExtra<Product>("cartItems") ?: emptyList()
        initRecyclerView(cartItems)

        binding.btnPay.setOnClickListener {
            Toast.makeText(this, "Pago realizado con éxito", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun initRecyclerView(cartItems: List<Product>) {

        cartListAdapter = CartListAdapter(cartItems)
        binding.rvCartItems.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCartItems.adapter = cartListAdapter
    }
}