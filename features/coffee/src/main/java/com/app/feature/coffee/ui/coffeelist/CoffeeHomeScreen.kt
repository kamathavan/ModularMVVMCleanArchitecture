package com.app.feature.coffee.ui.coffeelist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.feature.coffee.model.CoffeeUiState
import com.app.feature.coffee.ui.Screens
import com.app.feature.coffee.ui.coffeedetails.CoffeeDetailsScreen
import com.app.test.coffee.domain.model.Coffee

const val CONSTANT_ID_KEY  = "coffeeId"
@Composable
fun CoffeeHomeScreen(viewModel: CoffeeListViewModel = hiltViewModel()) {

    val state = viewModel.uiState.observeAsState()

    state.value?.let {
        when (it) {
            is CoffeeUiState.Loading -> LoadingContent()

            is CoffeeUiState.Success -> {
                CoffeeNavigation(it.coffees)
            }

            is CoffeeUiState.Error -> {
                ErrorScreen(it.message)
            }
        }
    }

}

@Composable
fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
    }
}

@Composable
fun ErrorScreen(error: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = error,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun CoffeeNavigation(coffees: List<Coffee>) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.CoffeeList.route) {

        composable(route = Screens.CoffeeList.route) {
            CoffeeListScreen(
                navigationController = navController,
                coffees,
            )
        }

        composable(route = "${Screens.CoffeeDetails.route}/{$CONSTANT_ID_KEY}",
            arguments = listOf(
                navArgument(name = CONSTANT_ID_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString(CONSTANT_ID_KEY)
            val coffee = coffees.first { coffee -> (coffee.id == id) }

            CoffeeDetailsScreen(
                coffee = coffee,
            )
        }
    }

    // test one

}