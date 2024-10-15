package no.kristiania.product.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import no.kristiania.product.data.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    suspend fun getProducts(): List<Product>

    @Query("SELECT * FROM Product WHERE :id = id")
    suspend fun getProductById(id: Int): Product?

    @Query("SELECT * FROM Product WHERE id IN (:idList)")
    suspend fun getProductsByIds(idList: List<Int>): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<Product>)
}