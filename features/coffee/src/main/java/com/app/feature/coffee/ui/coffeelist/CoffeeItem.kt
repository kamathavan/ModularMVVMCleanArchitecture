package com.app.feature.coffee.ui.coffeelist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.app.feature.coffee.ui.Screens
import com.app.test.coffee.domain.model.Coffee


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeListScreen(navigationController: NavController, coffees: List<Coffee>) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                AppBar(title = "Coffee list")
            },
        ) {
            LazyColumn(modifier = Modifier.padding(it)) {
                items(coffees) { coffee ->
                    CoffeeItem(
                        coffee = coffee,
                        navigationController,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeItem(
    coffee: Coffee,
    navigationController: NavController
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
        onClick = {
            navigationController.navigate(
                route = "${Screens.CoffeeDetails.route}/${coffee.id}"
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = coffee.title ?: "Title is not available",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )

            AsyncImage(
                model = coffee.image,
                contentScale = ContentScale.Crop,
                contentDescription = "image description",
                modifier = Modifier
                    .height(320.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
            )
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    TopAppBar(
        modifier = Modifier.height(50.dp),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(14.dp),
            )
        },
        scrollBehavior = scrollBehavior
    )
}