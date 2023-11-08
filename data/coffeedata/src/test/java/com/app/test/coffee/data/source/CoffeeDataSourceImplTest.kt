package com.app.test.coffee.data.source

import com.app.test.coffee.data.api.CoffeeApiServices
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CoffeeDataSourceImplTest {

    private lateinit var coffeeDataSourceImpl: CoffeeDataSourceImpl

    @MockK
    lateinit var coffeeApiServiecs: CoffeeApiServices


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        coffeeDataSourceImpl = CoffeeDataSourceImpl(
            coffeeApiServiecs
        )
    }

    @Test
    fun getCoffeeData() {
        
    }
}