package no.kristiania.product.screens.product_cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProductCartScreen(
    viewModel: CartViewModel,
    onBackButtonClick: () -> Unit = {},
    onProductClick: (productId: Int) -> Unit = {},
    onOrderClick: () -> Unit = {}
) {
    /* --- Collect state --- */
    val products = viewModel.productsCarts.collectAsState()
    val loading = viewModel.loading.collectAsState()
    val totalQuantity = viewModel.totalQuantity.collectAsState()
    val totalPrice = viewModel.totalPrice.collectAsState()


    if (loading.value) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(color = Color.White),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onBackButtonClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Navigate back to screen 1"
                )
            }
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = "Cart",
                style = MaterialTheme.typography.titleLarge
            )
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Shopping cart"
                    )
                    Text(
                        color = Color.White,
                        text = totalQuantity.value.toString(),
                        modifier = Modifier
                            .offset(x = (-10).dp, y = (-20).dp)
                            .background(
                                color = Color.Red,
                                shape = CircleShape
                            )
                            .padding(horizontal = 6.dp, vertical = 1.dp)
                    )
                }
                    Icon(
                        modifier = Modifier.clickable { onOrderClick() },
                        imageVector = Icons.Default.List,
                        contentDescription = "History order"
                    )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp)
        ) {
            items(products.value) { product ->
                CartItem(
                    product = product,
                    onProductClick = { onProductClick(product.id) },
                    onDeleteButton = { viewModel.deleteProductCart(product.id) }
                )
            }
        }

        Button(
            onClick = { viewModel.placeOrder() },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(top = 40.dp)
                .align(Alignment.BottomCenter),
        ) {
            Text(
                text = "Place order ($${totalPrice.value})",
            )
        }
    }
}

