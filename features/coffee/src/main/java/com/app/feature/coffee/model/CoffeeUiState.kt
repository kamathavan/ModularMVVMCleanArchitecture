package com.app.feature.coffee.model

import com.app.test.coffee.domain.model.Coffee

sealed class CoffeeUiState {
    object Loading : CoffeeUiState()
    data class Success(val coffees: List<Coffee>) : CoffeeUiState()
    data class Error(val message: String) : CoffeeUiState()
}
