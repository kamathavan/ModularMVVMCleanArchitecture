package com.app.test.coffee.data.source

import com.app.test.coffee.data.api.CoffeeApiServices
import com.app.test.coffee.data.model.CoffeeResponse
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CoffeeDataSourceImplTest {

    private lateinit var coffeeDataSourceImpl: CoffeeDataSourceImpl

    @MockK
    lateinit var coffeeApiServices: CoffeeApiServices


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        coffeeDataSourceImpl = CoffeeDataSourceImpl(
            coffeeApiServices
        )
    }

    @Test
    fun `Given coffee api service response, When Coffee data source called, Then return the list of coffee responses`() =
        runTest {
            //Given
            coEvery { coffeeApiServices.getCoffeeServices() } returns getCoffeeResponse()

            //When
            val result = coffeeDataSourceImpl.getCoffeeData()

            //Then
            assertThat(result).isEqualTo(getCoffeeResponse())

        }

    @Test
    fun `Given coffee api service empty list , When Coffee data source called, Then return the list of empty coffee responses`() =
        runTest {
            //Given
            coEvery { coffeeApiServices.getCoffeeServices() } returns emptyList()

            //When
            val result = coffeeDataSourceImpl.getCoffeeData()

            //Then
            assertThat(result).isEqualTo(emptyList<CoffeeResponse>())

        }

    //region for stubbing response
    private fun getCoffeeResponse(): List<CoffeeResponse> = listOf(
        CoffeeResponse(
            title = "some_title",
            description = "some_description",
            image = "some_image",
            ingredients = listOf("some_ingridient1", "some_ingridient2"),
            id = "some_id"
        )
    )
    //endregion
}