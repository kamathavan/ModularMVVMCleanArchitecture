package com.app.test.coffee.domain.repository

import com.app.test.coffee.domain.model.RequestState
import com.app.test.coffee.domain.model.Coffee


interface CoffeeRepository {
    suspend fun getCoffees(): RequestState<List<Coffee>>
}