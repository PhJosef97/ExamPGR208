package no.kristiania.product

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import no.kristiania.product.data.ProductRepository
import no.kristiania.product.screens.product_cart.CartViewModel
import no.kristiania.product.screens.product_cart.ProductCartScreen
import no.kristiania.product.screens.product_details.ProductDetailsScreen
import no.kristiania.product.screens.product_details.ProductDetailsViewModel
import no.kristiania.product.screens.product_list.ProductListScreen
import no.kristiania.product.screens.product_list.ProductListViewModel
import no.kristiania.product.screens.product_order_history.OrderScreen
import no.kristiania.product.screens.product_order_history.OrderViewModel
import no.kristiania.product.ui.theme.ProductTheme

class MainActivity : ComponentActivity() {

    /* --- COLLECT VIEW MODEL --- */

    private val _productListViewModel: ProductListViewModel by viewModels()
    private val _productDetailsViewModel: ProductDetailsViewModel by viewModels()
    private val _cartViewModel: CartViewModel by viewModels()
    private val _orderViewModel: OrderViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ProductRepository.initiateAppDatabase(applicationContext)

        setContent {
            ProductTheme {
                val navController = rememberNavController()

                /* START */
                NavHost(
                    navController = navController,
                    startDestination = "productListScreen"
                ) {

                    /* --- PRODUCT SCREEN  --- */

                   composable(route = "productListScreen") {
                       LaunchedEffect(Unit) {
                           // La den loading ved start
                           _productListViewModel.loadProducts()
                       }
                       ProductListScreen(
                           viewModel = _productListViewModel,
                           onProductClick = {
                                   productId -> navController.navigate("productDetailsScreen/$productId")
                           },
                           onCartClick = { navController.navigate("cartScreen") },
                           onOrderClick = { navController.navigate("orderScreen") }
                       )
                   }

                    /* --- DETAIL SCREEN--- */

                    composable(
                        route = "productDetailsScreen/{productId}",
                        arguments = listOf(
                            navArgument(name = "productId") {
                                type = NavType.IntType
                            }
                        )
                    ) {backStackEntry ->
                        val productId = backStackEntry.arguments?.getInt("productId") ?: -1

                        LaunchedEffect(productId) {
                            _productDetailsViewModel.setSelectedProduct(productId)
                        }

                        ProductDetailsScreen(
                            viewModel = _productDetailsViewModel,
                            onBackButtonClick = { navController.popBackStack() },
                            onCartClick = { navController.navigate("cartScreen") },
                            onOrderClick = { navController.navigate("orderScreen") }
                        )
                    }

                    /* --- CART SCREEN  --- */

                    composable(route = "cartScreen") {
                        LaunchedEffect(Unit) {
                            _cartViewModel.loadCarts()
                        }
                        ProductCartScreen(
                            viewModel = _cartViewModel,
                            onBackButtonClick = { navController.popBackStack() },
                            onProductClick = { productId -> navController.navigate("productDetailsScreen/$productId") },
                            onOrderClick = { navController.navigate("orderScreen") }
                        )
                    }

                    /* --- ORDER SCREEN --- */

                    composable(route = "orderScreen") {
                            LaunchedEffect(Unit) {
                            _orderViewModel.loadOrders()
                        }
                        OrderScreen(
                            viewModel = _orderViewModel,
                            onBackButtonClick = { navController.popBackStack() },
                            onCartClick = {navController.navigate("cartScreen")}
                        )
                    }


                }
            }
        }
    }
}

