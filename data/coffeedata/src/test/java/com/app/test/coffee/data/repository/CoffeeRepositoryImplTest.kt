package com.app.test.coffee.data.repository

import com.app.test.coffee.data.mapper.CoffeeDataMapper
import com.app.test.coffee.data.model.CoffeeResponse
import com.app.test.coffee.data.source.CoffeeDataSource
import com.app.test.coffee.domain.model.Coffee
import com.app.test.coffee.domain.model.RequestState
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CoffeeRepositoryImplTest {

    private lateinit var coffeeRepository: CoffeeRepositoryImpl

    @MockK
    lateinit var coffeeDataSource: CoffeeDataSource

    @MockK
    private lateinit var coffeeDataMapper: CoffeeDataMapper

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        coffeeRepository = CoffeeRepositoryImpl(
            coffeeDataSource = coffeeDataSource,
            coffeeDataMapper = coffeeDataMapper
        )
    }

    //region for unit test coffee repository
    @Test
    fun `Given coffee data source throw error, When coffee repository failure, Then return request failure  state`() =
        runTest {
            // given
            val expectedException = Exception("test exception")

            coEvery {
                coffeeDataSource.getCoffeeData()
            } throws expectedException

            val expectedResult = RequestState.FailureState(expectedException)

            // when
            val actualResponse = coffeeRepository.getCoffees()

            // then
            assertThat(actualResponse).isEqualTo(expectedResult)
        }

    @Test
    fun `Given coffee data source, When coffee repository success, Then return request list of getCoffees response state`() =
        runTest {
            // given
            `set the success state list of coffee  response`()
            val expectedResult = RequestState.SuccessState(getTransformedCoffeeResponse())

            // when
            val actualResponse = coffeeRepository.getCoffees()


            // then
            assertThat(actualResponse).isEqualTo(expectedResult)
        }
    // endregion

    //region for stubbing response
    private fun `set the success state list of coffee  response`() {
        coEvery { coffeeDataSource.getCoffeeData() } returns getCoffeeResponse()

        every {
            coffeeDataMapper.transformCoffeeData(
                coffeeResponse = getCoffeeResponse()
            )
        } returns getTransformedCoffeeResponse()
    }

    private fun getCoffeeResponse(): List<CoffeeResponse> = listOf(
        CoffeeResponse(
            title = "some_title",
            description = "some_description",
            image = "some_image",
            ingredients = listOf<String>("some_ingridient1", "some_ingridient2"),
            id = "some_id"
        )
    )

    private fun getTransformedCoffeeResponse(): List<Coffee> = listOf(
        Coffee(
            title = "some_title",
            description = "some_description",
            image = "some_image",
            ingredients = listOf<String>("some_ingridient1", "some_ingridient2"),
            id = "some_id"
        )
    )
    // endregion
}