package com.app.feature.coffee.ui.coffeelist

import com.app.feature.coffee.model.CoffeeUiState
import com.app.test.coffee.domain.model.Coffee
import com.app.test.coffee.domain.model.RequestState
import com.app.test.coffee.domain.usecase.GetHotCoffeeUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo

@OptIn(ExperimentalCoroutinesApi::class)
class CoffeeListViewModelTest {

    @get:Rule
    val rule = MainDispatcherRule()

    @MockK
    private lateinit var getHotCoffeeUseCase: GetHotCoffeeUseCase

    private lateinit var viewModel: CoffeeListViewModel


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = CoffeeListViewModel(getHotCoffeeUseCase = getHotCoffeeUseCase)
    }

    @Test
    fun `Given coffee list, When coffee ui state success, Then return list of Coffee list response`() =
        runTest {
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

            coEvery { getHotCoffeeUseCase.invoke() } returns RequestState.SuccessState(coffeeList)

            // When
            viewModel.getCoffeeList()

            // Then
            assertThat(viewModel.uiState.value).isEqualTo(CoffeeUiState.Success(coffees = coffeeList))
        }

    @Test
    fun `Given coffee response, When coffee ui state failure, Then return  error response`() {
        //Given
        val exceptionResponse = Throwable("test exception")

        val expectedResult = RequestState.FailureState(error = exceptionResponse)
        coEvery { getHotCoffeeUseCase.invoke() } returns expectedResult

        //When
        viewModel.getCoffeeList()

        //Then
        expectThat(viewModel.uiState.value).isA<CoffeeUiState.Error>().and {
            get { this.message }.isEqualTo(exceptionResponse.toString())
        }

    }

}
