package no.kristiania.product.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cart (
    @PrimaryKey
    val productId: Int,
    val quantity: Int,
    val price: Double,
)