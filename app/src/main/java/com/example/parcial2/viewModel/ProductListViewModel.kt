package com.example.parcial2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parcial2.data.Cart
import com.example.parcial2.data.Product
import com.example.parcial2.data.ProductList

class ProductListViewModel: ViewModel() {
    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> = _productList

    private val _cartItems = MutableLiveData<List<Product>>()
    val cartItems: LiveData<List<Product>> = _cartItems

    init{
        _productList.value = ProductList.listOfProducts
        _cartItems.value = Cart.cartItems
    }

    fun addProductToCart(product: Product) {
        Cart.cartItems.add(product)
        _cartItems.value = Cart.cartItems
    }

    fun getCartItemsLiveData(): LiveData<List<Product>> {
        return _cartItems
    }

    fun getTotalPrice(): String {
        return String.format("%.2f", Cart.cartItems.sumOf { it.price })
    }

}