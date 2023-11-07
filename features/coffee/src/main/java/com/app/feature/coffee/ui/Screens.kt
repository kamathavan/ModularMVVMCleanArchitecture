package com.app.feature.coffee.ui

sealed class Screens(
    val route: String,
    val screenName: String
) {
    object CoffeeList : Screens(
        route = "coffee_list",
        screenName = "CoffeeList"
    )

    object CoffeeDetails : Screens(
        route = "coffee_details",
        screenName = "Coffee Details"
    )
}
