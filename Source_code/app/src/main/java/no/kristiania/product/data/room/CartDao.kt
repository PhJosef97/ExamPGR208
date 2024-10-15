package no.kristiania.product.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import no.kristiania.product.data.Cart

//
@Dao
interface CartDao {
    @Query("SELECT * FROM Cart")
    suspend fun getCartList(): List<Cart>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCart(cart: Cart)

    @Delete
    suspend fun removeCart(cart: Cart)

    @Query("DELETE FROM Cart")
    suspend fun clearCart()

}