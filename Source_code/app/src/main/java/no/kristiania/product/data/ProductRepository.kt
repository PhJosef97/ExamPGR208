package no.kristiania.product.data

import android.content.Context
import android.util.Log
import androidx.room.Room
import no.kristiania.product.data.room.AppDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductRepository {
    private val _httpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()

    private val _retrofit =
        Retrofit.Builder()
            .client(_httpClient)
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val _productService = _retrofit.create(ProductService::class.java)

    // Declaration ved å lage _appDatabase.henteFraDao()
    private lateinit var _appDatabase: AppDatabase
    private val _productDao by lazy { _appDatabase.productDao() }
    private val _cartDao by lazy { _appDatabase.cartDao()}
    private val _orderDao by lazy { _appDatabase.orderDao() }

    // Kjører etter når database har blitt bygd
    fun initiateAppDatabase(context: Context) {
        _appDatabase = Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "product-database"
        ).fallbackToDestructiveMigration().build()
    }

                        /* --- PRODUCTS FUNCTION --- */

    suspend fun getProducts(): List<Product> {
        try {
            val response = _productService.getProducts()

            if (response.isSuccessful) {

                val products = response.body() ?: emptyList()

                _productDao.insertProducts(products)
                return _productDao.getProducts()

            } else {
                throw Exception("getting products not succeed")
            }

        } catch (e: Exception) {
            Log.e("ProductRepository", "Failed to get list of products", e)
            return _productDao.getProducts()
        }
    }

    suspend fun getProductById(productId: Int): Product? {
        return _productDao.getProductById(productId)
    }

    suspend fun getProductsByIds(idList: List<Int> ): List<Product> {
        return _productDao.getProductsByIds(idList)
    }

                        /* --- CART FUNCTION --- */

    suspend fun getCartList(): List<Cart> {
        return _cartDao.getCartList()
    }

    suspend fun addCart(cart: Cart) {
        _cartDao.addCart(cart)
    }

    suspend fun removeCart(cart: Cart) {
        _cartDao.removeCart(cart)
    }

    suspend fun clearCart() {
        _cartDao.clearCart()
    }

                        /* --- ORDER FUNCTION --- */

    suspend fun getOrderList(): List<Order> {
        return _orderDao.getOrderList()
    }

    suspend fun addOrder(order: Order) {
        return _orderDao.addOrder(order)
    }

    }
