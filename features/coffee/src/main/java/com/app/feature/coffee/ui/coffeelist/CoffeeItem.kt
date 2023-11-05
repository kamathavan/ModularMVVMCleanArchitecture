package com.app.feature.coffee.ui.coffeelist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.test.coffee.domain.model.Coffee

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeItem(
    coffee: Coffee,
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
        onClick = {
           /* navigationController.navigate(
                route = "${Screens.CoffeeDetails.route}/$coffeeId"
            )*/
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            //val painter = rememberImagePainter(data = coffee.image)

            Text(
                text = coffee.title ?: "Title is not available",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )

           /* Image(
                painter = painter,
                contentDescription = "Coffee Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(320.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
            )*/

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(
                    4.dp,
                    alignment = Alignment.Start
                ),
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                items(items = coffee.ingredients) { ingredient ->
                    IngredientChip(text = ingredient)
                }
            }
        }

    }
}

