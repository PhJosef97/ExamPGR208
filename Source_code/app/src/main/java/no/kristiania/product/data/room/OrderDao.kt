package no.kristiania.product.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import no.kristiania.product.data.Order

@Dao
interface OrderDao {
    @Query("SELECT * FROM Order_List")
    suspend fun getOrderList(): List<Order>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrder(order: Order)


}