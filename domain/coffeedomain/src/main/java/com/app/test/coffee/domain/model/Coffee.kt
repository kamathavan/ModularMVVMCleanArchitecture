package com.app.test.coffee.domain.model

data class Coffee(
    val title: String?,
    val description: String,
    val ingredients: List<String>,
    val image: String,
    val id: String,
)