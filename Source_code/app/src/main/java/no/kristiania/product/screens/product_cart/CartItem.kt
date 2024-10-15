package no.kristiania.product.screens.product_cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import no.kristiania.product.data.Product

@Composable
fun CartItem(
    product: Product,
    onProductClick: () -> Unit = {},
    onDeleteButton: () -> Unit = {}
) {
    // Section

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(45.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.90f)
                .padding(
                    horizontal = 12.dp,
                    vertical = 8.dp
                )
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(10)
                )
                .background(color = Color.White)
                .clickable { onProductClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(108.dp, 108.dp)
                    .background(color = Color.Gray),
                model = product.image,
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                contentDescription = "Image of ${product.title}"
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text(
                    text = product.title,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = product.category,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )
            }
        }

        IconButton(
            onClick = { onDeleteButton() },
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete product",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartItemPreview() {
    CartItem(
        product = Product(
            id = 1,
            title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            category = "men's clothing",
            description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
            price = 109.95,
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
        )
    )
}