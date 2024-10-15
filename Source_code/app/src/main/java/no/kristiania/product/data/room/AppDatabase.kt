package no.kristiania.product.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import no.kristiania.product.data.Cart
import no.kristiania.product.data.Order
import no.kristiania.product.data.Product

// Lager tabeller. Oppdaterer version hvis det er noe oppdatering i datatabase
@Database(
    entities = [Product::class, Cart::class, Order::class],
    version = 14,
    exportSchema = false
    )
// Legger inn Dao som er laget med interface
@TypeConverters(ProductTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
    abstract fun orderDao(): OrderDao
}