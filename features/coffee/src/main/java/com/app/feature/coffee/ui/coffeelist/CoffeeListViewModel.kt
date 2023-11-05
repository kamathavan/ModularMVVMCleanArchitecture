package com.app.feature.coffee.ui.coffeelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.feature.coffee.model.CoffeeUiState
import com.app.test.coffee.domain.model.RequestState
import com.app.test.coffee.domain.usecase.GetHotCoffeeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeListViewModel @Inject constructor(
    private val getHotCoffeeUseCase: GetHotCoffeeUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<CoffeeUiState>()
    val uiState: LiveData<CoffeeUiState> = _uiState

    init {
        getCoffeeList()
    }

    fun getCoffeeList() {
        _uiState.value = CoffeeUiState.Loading

        viewModelScope.launch {
            when (val result = getHotCoffeeUseCase()) {
                is RequestState.SuccessState -> {
                    _uiState.value = CoffeeUiState.Success(coffees = result.data)
                }

                is RequestState.FailureState -> {
                    _uiState.value = CoffeeUiState.Error(message = result.error.toString())
                }
            }
        }
    }
}