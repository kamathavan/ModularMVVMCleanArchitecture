package com.app.test.coffee.data.source

import com.app.test.coffee.data.api.CoffeeApiServices
import com.app.test.coffee.data.model.CoffeeResponse
import javax.inject.Inject

class CoffeeDataSourceImpl @Inject constructor(
    private val coffeeApiService: CoffeeApiServices
) : CoffeeDataSource {
    override suspend fun getCoffeeData(): List<CoffeeResponse> =
        coffeeApiService.getCoffeeServices()
}