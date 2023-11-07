package com.app.feature.coffee.ui.coffeelist

import com.app.feature.coffee.model.CoffeeUiState
import com.app.feature.coffee.util.BaseCoroutineTestWithTestDispatcherProviderAndInstantTaskExecutorRule
import com.app.test.coffee.domain.model.Coffee
import com.app.test.coffee.domain.model.RequestState
import com.app.test.coffee.domain.usecase.GetHotCoffeeUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class CoffeeListViewModelTest :
    BaseCoroutineTestWithTestDispatcherProviderAndInstantTaskExecutorRule(
        UnconfinedTestDispatcher()
    ) {


    @MockK
    lateinit var getHotCoffeeUseCase: GetHotCoffeeUseCase

    private lateinit var viewModel: CoffeeListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
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
    fun `Given coffee response, When coffee ui state failure, Then return  error response`() =
        runTest {
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
