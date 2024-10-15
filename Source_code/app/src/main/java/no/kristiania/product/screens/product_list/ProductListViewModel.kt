package no.kristiania.product.screens.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import no.kristiania.product.data.Product
import no.kristiania.product.data.ProductRepository

class ProductListViewModel : ViewModel() {
    // --- STATES ---
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    private val products = _products.asStateFlow()

    // Search state
    private val _searchFilter = MutableStateFlow("")
    val searchFilter = _searchFilter.asStateFlow()

    // looking for same category
    val filteredProducts = combine(products, searchFilter) { product, filterText ->
        product.filter{ it.category.startsWith(filterText) }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = emptyList()
    )

    // FILTER VALUE
    fun onFilterTextChanged (text: String) {
        _searchFilter.value = text
    }

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            _products.value = ProductRepository.getProducts()
            _loading.value = false
        }
    }
}