package com.app.test.coffee.data.mapper

import com.app.test.coffee.data.model.CoffeeResponse
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class CoffeeDataMapperTest {

    private lateinit var coffeeMapper: CoffeeDataMapper

    @Before
    fun setUp() {
        coffeeMapper = CoffeeDataMapper()
    }

    @Test
    fun `Given list of coffee response, When transform, Then return domain model list of coffee response`() {
        // given
        val coffeeResponseList = listOf<CoffeeResponse>(
            CoffeeResponse(
                title = "some_title",
                description = "some_description",
                image = "some_image",
                ingredients = listOf<String>("some_ingridient1", "some_ingridient2"),
                id = "some_id"
            )
        )
        // when
        val result = coffeeMapper.transformCoffeeData(coffeeResponse = coffeeResponseList)

        // then
        val expectedId = "some_id"
        val expectedDescription = "some_description"
        val expectedImage = "some_image"

        assertThat(expectedId).isEqualTo(result[0].id)
        assertThat(expectedDescription).isEqualTo(result[0].description)
        assertThat(expectedImage).isEqualTo(result[0].image)
    }
}