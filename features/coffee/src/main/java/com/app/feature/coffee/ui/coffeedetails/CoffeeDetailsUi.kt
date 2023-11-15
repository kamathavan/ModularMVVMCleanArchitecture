@file:OptIn(ExperimentalMaterial3Api::class)

package com.app.feature.coffee.ui.coffeedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.app.feature.coffee.R
import com.app.feature.coffee.ui.coffeelist.AppBar
import com.app.feature.coffee.ui.theme.Brown
import com.app.test.coffee.domain.model.Coffee

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeDetailsScreen(coffee: Coffee?) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                AppBar(title = stringResource(id = R.string.feature_coffee_details_title))
            },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxSize()
                            .padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = coffee?.title ?: stringResource(R.string.feature_coffee_no_title_message),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Start
                            )
                        }

                        AsyncImage(
                            model = coffee?.image,
                            contentScale = ContentScale.Crop,
                            contentDescription = stringResource(R.string.feature_coffee_image_description),
                            modifier = Modifier
                                .height(320.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                        )
                        CoffeeContentTitle(contentTitle = stringResource(R.string.feature_coffee_title_description))

                        Text(
                            text = coffee?.description ?: stringResource(R.string.feature_coffee_description_is_not_available),
                            textAlign = TextAlign.Justify
                        )

                        CoffeeContentTitle(contentTitle = stringResource(R.string.feature_coffee_title_ingredients))

                        CoffeeIngredientDetails(
                            ingredients = coffee?.ingredients ?: stringResource(R.string.feature_coffee_ingredient_is_not_available)
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun CoffeeContentTitle(contentTitle: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = contentTitle,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
    }

}

@Composable
fun CoffeeIngredientDetails(ingredients: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = ingredients,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start,
            color = Brown,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(2.dp)
        )
    }

}


