package no.kristiania.product.screens.UI_beginning
/*

// Dette er preview detail screen min for å designe UI. Jeg har laget også med med ProductlistScreen men
// fant ut at jeg trengte item).

*/
/*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Preview
@Composable
fun ProductDetailsScreen() {

    Column (modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton (
                onClick = { /**/ }
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
                IconButton(onClick = { /**/ }) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Shopping cart"
                    )
                }
                IconButton(onClick = { /**/ }) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "History order"
                    )
                }
            }
        }
        Divider()

        Column (
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(300.dp)
                    .background(color = Color.Gray),
                model = productDetails.image,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = "Image of ${productDetails.title}",
            )
            Text(
                text = productDetails.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyLarge,
                text = productDetails.description,
                overflow = TextOverflow.Ellipsis
            )
            Button(
                onClick = { /**/ },
                modifier = Modifier.fillMaxWidth(0.9f),
                border = BorderStroke(2.dp, Color.Black),
                contentPadding = PaddingValues()
            ) {
                Text(
                    text = "Add to cart",
                )
            }
        }

    }
}
*/