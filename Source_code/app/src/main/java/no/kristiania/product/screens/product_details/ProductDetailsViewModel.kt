package no.kristiania.product.screens.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import no.kristiania.product.data.Cart
import no.kristiania.product.data.Product
import no.kristiania.product.data.ProductRepository

class ProductDetailsViewModel : ViewModel() {
    // --- STATES ---
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProductDetails = _selectedProduct.asStateFlow()

    // INSERT NEW DATA TO CART
    fun addProductCart(productId: Int, price: Double, quantity: Int) {
        viewModelScope.launch {
            ProductRepository.addCart(Cart(productId, quantity, price))
        }
    }

    // PRODUCT BY ID
    fun setSelectedProduct(productId: Int) {
        viewModelScope.launch{
            _loading.value = true
            _selectedProduct.value = ProductRepository.getProductById(productId)
            _loading.value = false
        }
    }
}