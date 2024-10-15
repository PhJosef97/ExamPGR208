package no.kristiania.product.screens.product_cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import no.kristiania.product.data.Cart
import no.kristiania.product.data.Order
import no.kristiania.product.data.Product
import no.kristiania.product.data.ProductRepository

class CartViewModel : ViewModel() {

    // --- STATES --
    private val _productsCarts = MutableStateFlow<List<Product>>(emptyList())
    val productsCarts = _productsCarts.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _totalQuantity = MutableStateFlow(0)
    val totalQuantity = _totalQuantity.asStateFlow()

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice = _totalPrice.asStateFlow()


    init {
        loadCarts()
    }


    fun loadCarts() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val listOfCarts = ProductRepository.getCartList()
            _productsCarts.value = ProductRepository.getProductsByIds(listOfCarts.map { it.productId })
            updateTotalQuantity(listOfCarts)
            _totalPrice.value = calculateTotalPrice(listOfCarts)
            _loading.value = false
        }
    }

    // ADD TO ORDER CLASS
    fun placeOrder() {
        viewModelScope.launch(Dispatchers.IO) {
            val listOfCarts = ProductRepository.getCartList()
            val orderIds = listOfCarts.map { it.productId } // Create a list with the product IDs
            val totalQuantity = listOfCarts.sumOf { it.quantity } // Calculate the total quantity
            val totalPrice = listOfCarts.sumOf { it.price } // Calculate the total price
            ProductRepository.addOrder(Order(productIds = orderIds, quantity = totalQuantity, totalPrice = totalPrice))
            ProductRepository.clearCart()
            loadCarts()
        }
    }


    // DELETE IDENTITY TO PRODUCT ID FROM CART
    fun deleteProductCart(productId: Int) {
        viewModelScope.launch {
            val cartItem = ProductRepository.getCartList().find { it.productId == productId }
            if (cartItem != null) {
                ProductRepository.removeCart(cartItem)
                loadCarts()
            }
        }
    }

    // COUNTS PRODUCT
    private fun updateTotalQuantity(cartList: List<Cart>) {
        _totalQuantity.value = cartList.sumOf { it.quantity }
    }

    // SUMS UP THE PRICE
    private fun calculateTotalPrice(cartList: List<Cart>): Double {
        return cartList.sumOf { it.price }
    }
}
