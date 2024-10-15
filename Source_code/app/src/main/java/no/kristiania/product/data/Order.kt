package no.kristiania.product.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import no.kristiania.product.data.room.ProductTypeConverter

// Database
@Entity(tableName = "Order_List")
@TypeConverters(ProductTypeConverter::class)
data class Order(
    @PrimaryKey(autoGenerate = true)
    val orderId: Int = 0,
    val productIds: List<Int>,
    val quantity: Int,
    val totalPrice: Double,
)
