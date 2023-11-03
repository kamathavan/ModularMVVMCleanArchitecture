package com.app.test.coffee.data.mapper

import com.app.test.coffee.data.model.CoffeeResponse
import com.app.test.coffee.domain.model.Coffee
import javax.inject.Inject

class CoffeeDataMapper @Inject constructor() {

    fun transformCoffeeData(coffeeResponse: List<CoffeeResponse>): List<Coffee> {
        return coffeeResponse.map { data ->
            Coffee(
                title = data.title,
                description = data.description,
                image = data.image,
                ingredients = data.ingredients,
                id = data.id
            )
        }
    }
}