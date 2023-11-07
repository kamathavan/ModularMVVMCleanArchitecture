package com.app.feature.coffee.ui.coffeelist

import com.app.feature.coffee.model.CoffeeUiState
import com.app.test.coffee.domain.model.Coffee
import com.app.test.coffee.domain.model.RequestState
import com.app.test.coffee.domain.usecase.GetHotCoffeeUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CoffeeListViewModelTest {

    private val getHotCoffeeUseCase: GetHotCoffeeUseCase = mockk()

    private lateinit var viewModel: CoffeeListViewModel

    private lateinit var viewStates: MutableList<CoffeeUiState>

    @get:Rule
    val rule = MainDispatcherRule()

    @Before
    fun setUp() {
        //MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = CoffeeListViewModel(getHotCoffeeUseCase = getHotCoffeeUseCase)
        viewModel.uiState.observeForever {
            viewStates.add(it)
        }
    }

    @Test
    fun `Given coffee list, When coffee ui state success, Then return list of Coffee list response`()= runTest {
        // Given
        val coffeeList = listOf(
            Coffee(
                title = "some_title",
                description = "some_description",
                image = "some_image",
                ingredients = "some_ingridient,some_ingridient2",
                id = "some_id"
            )
        )

        coEvery { getHotCoffeeUseCase() } returns RequestState.SuccessState(coffeeList)

        // When
        viewModel.getCoffeeList()

        // Then
        assertThat(viewModel.uiState.value).isEqualTo(CoffeeUiState.Success(coffees = coffeeList))
    }

    @Test
    fun `Given coffee response, When coffee ui state failure, Then return  error response`() {
        //Given
        val exceptionResponse = Exception("test exception")

        val expectedResult = RequestState.FailureState(error = exceptionResponse)
        coEvery { getHotCoffeeUseCase.invoke() } returns expectedResult

        //When
        viewModel.getCoffeeList()

        //Then
        assertThat(viewModel.uiState.value).isEqualTo(CoffeeUiState.Error("test exception"))

    }

}
