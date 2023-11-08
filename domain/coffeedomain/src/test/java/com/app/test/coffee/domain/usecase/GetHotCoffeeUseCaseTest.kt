package com.app.test.coffee.domain.usecase

import com.app.test.coffee.domain.model.Coffee
import com.app.test.coffee.domain.model.RequestState
import com.app.test.coffee.domain.repository.CoffeeRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@OptIn(ExperimentalCoroutinesApi::class)
class CoffeeUseCaseTest {

    private lateinit var cut: GetHotCoffeeUseCase

    @MockK
    lateinit var coffeeRepository: CoffeeRepository


    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = GetHotCoffeeUseCase(
            coffeeRepository = coffeeRepository
        )
    }

    @Test
    fun `invoke with success response state`() = runTest {
        //given
        val listOfCoffee = mutableListOf<Coffee>(
            Coffee(
                title = "some_title",
                description = "some_description",
                image = "some_image",
                ingredients = "some_ingridient1,some_ingridient2",
                id = "some_id"
            )
        )

        val expectedResult = RequestState.SuccessState(data = listOfCoffee)

        coEvery { coffeeRepository.getCoffees() } returns expectedResult

        //when
        val actualResult = cut.invoke()

        //Then
        expectThat(actualResult).isEqualTo(expectedResult)
    }


    @Test
    fun `invoke with failure response state`() = runTest {
        //given
        val exceptionResponse = Exception("test exception")
        val expectedResult = RequestState.FailureState(error = exceptionResponse)

        coEvery { coffeeRepository.getCoffees() } returns expectedResult

        //when
        val actualResult = cut.invoke()

        //Then
        expectThat(actualResult).isEqualTo(expectedResult)
    }

}