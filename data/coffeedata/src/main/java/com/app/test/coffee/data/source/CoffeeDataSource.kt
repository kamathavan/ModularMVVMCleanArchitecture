package com.app.test.coffee.data.source

import com.app.test.coffee.data.model.CoffeeResponse

interface CoffeeDataSource {
    suspend fun getCoffeeData(): List<CoffeeResponse>
}