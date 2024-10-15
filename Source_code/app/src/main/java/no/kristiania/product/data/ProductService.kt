package no.kristiania.product.data

import retrofit2.Response
import retrofit2.http.GET

// DATA SOURCE FROM (https://fakestoreapi.com/)
interface ProductService {

    @GET("products")
    suspend fun getProducts() : Response<List<Product>>

    // IKKE I BRUK FORDI DAO ALLEREDE GJØR JOBBEN
    /*@GET("products/{id}")
    suspend fun getProductById(
        @Path("id")
        id: Int
    ) : Response<Product>
     */
}