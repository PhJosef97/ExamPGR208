package no.kristiania.product.screens.product_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import no.kristiania.product.ui.theme.Purple80


@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel,
    onBackButtonClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onOrderClick: () -> Unit = {}
) {
    /* --- Collect state --- */
    val loading = viewModel.loading.collectAsState()
    val productState = viewModel.selectedProductDetails.collectAsState()

    if(loading.value) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    val product = productState.value
    if(product == null) {
        Text(text = "Failed to get product details. Selected product object is NULL!")
        return
    }


    // Header
    Column (modifier = Modifier
        .fillMaxSize()
    )
    {
        Row(
        modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
    ) {
            IconButton (
                onClick = { onBackButtonClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Navigate back to screen 1"
                )
            }
            Text (
                modifier = Modifier
                    .padding(8.dp),
                text = "Product Details",
                style = MaterialTheme.typography.titleLarge
            )
            Row (
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onCartClick() }) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Shopping cart"
                    )
                }
                IconButton(onClick = { onOrderClick() }) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "History order"
                    )
                }
            }
        }
        Divider()

        // Body
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.DarkGray),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = CenterHorizontally,
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(180.dp),
                model = product.image,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = "Image of ${product.title}",
            )
            Text(
                modifier = Modifier
                    .padding(20.dp),
                text = product.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    modifier = Modifier
                        .padding(20.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    text = product.description,
                    color = Purple80,
                    overflow = TextOverflow.Ellipsis,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Monospace
                )
            }

            Button(
                // WHENEVER CLICK WILL ADDING THOSE ID, PRICE OF THE ID AND INCREMENT QUANTITY
                onClick = { viewModel.addProductCart(
                    productId = product.id,
                    price = product.price,
                    quantity = 1) },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(alignment = CenterHorizontally),
            ) {
                Text(
                    text = "Add to cart",
                )
            }
        }

    }
}
