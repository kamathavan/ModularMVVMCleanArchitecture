package com.app.test.coffee.data.api

import com.app.test.coffee.data.model.CoffeeResponse
import com.app.test.coffee.data.utils.Constants.COFFEES_ENDPOINT
import retrofit2.http.GET

interface CoffeeApiServices {
    @GET(COFFEES_ENDPOINT)
    suspend fun getCoffeeServices(): List<CoffeeResponse>
}