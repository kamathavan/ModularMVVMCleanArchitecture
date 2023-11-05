package com.app.feature.coffee.ui.coffeelist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.app.feature.coffee.model.CoffeeUiState
import com.app.test.coffee.domain.model.Coffee

@Composable
fun CoffeeHomeScreen(viewModel: CoffeeListViewModel = hiltViewModel()) {

    val state = viewModel.uiState.observeAsState()

    state.value?.let {
        when (it) {
            is CoffeeUiState.Loading -> LoadingContent()

            is CoffeeUiState.Success -> {
                CoffeeListScreen(it.coffees)
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
        CircularProgressIndicator()
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
fun CoffeeListScreen(coffees: List<Coffee>) {

    LazyColumn(modifier = Modifier.padding(20.dp)) {
        items(coffees) { coffee ->
            CoffeeItem(
                coffee = coffee,
            )
        }
    }
}
