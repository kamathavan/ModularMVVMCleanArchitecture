package com.app.test.coffee.data.model

data class CoffeeResponse(
    val title: String?,
    val description: String,
    val ingredients: List<String>,
    val image: String,
    val id: String,
)