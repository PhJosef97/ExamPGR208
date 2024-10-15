package no.kristiania.product.screens.product_order_history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import no.kristiania.product.data.Order
import no.kristiania.product.data.ProductRepository

class OrderViewModel : ViewModel() {
    // --- STATES ---
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders = _orders.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _productIdsTitles = MutableStateFlow<Map<Int, String>>(emptyMap())
    val productIdsTitles = _productIdsTitles.asStateFlow()

    init {
        loadOrders()
    }

    fun loadOrders() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val listOfOrders = ProductRepository.getOrderList()
            val orderDetails = mapOrders(listOfOrders)
            _orders.value = orderDetails

            // HENTER TITLES OG OPPDATERE TITLES
            val productTitlesMap = mutableMapOf<Int, String>()
            orderDetails.forEach { order ->
                order.productIds.forEach { productId ->
                    val product = ProductRepository.getProductById(productId)
                    product?.let {
                        productTitlesMap[productId] = it.title
                    }
                }
            }
            _productIdsTitles.value = productTitlesMap

            _loading.value = false
        }
    }

    private fun mapOrders(listOfOrders: List<Order>): List<Order> {
        return listOfOrders.map { order ->
            Order(
                orderId = order.orderId,
                productIds = order.productIds,
                quantity = order.quantity,
                totalPrice = order.totalPrice
                )
            }
        }
    }
