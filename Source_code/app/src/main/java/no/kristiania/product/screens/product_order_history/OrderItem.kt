package no.kristiania.product.screens.product_order_history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.kristiania.product.data.Order

@Composable
fun OrderItem(
    order: Order,
    productTitles: Map<Int, String>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 8.dp
                )
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(10)
                )
                .background(color = Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text(
                    text = "Order ID: ${order.orderId}",
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column {
                    for (productId in order.productIds) {
                        Text(
                            text = "$productId. ${productTitles[productId] ?: "Product title not available"}",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.Gray,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$${order.totalPrice} (${order.quantity} items)",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderItemPreview() {
    OrderItem(
        order = Order(
            orderId = 1,
            productIds = listOf(1,2,3),
            quantity = 0,
            totalPrice = 0.0
        ),
        productTitles = listOf("Product", "Product", "Product").withIndex().associate { it.index to it.value }    )
}