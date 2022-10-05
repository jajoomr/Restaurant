package com.mayurjajoo.restaurant.model

data class Category(
    val id: String,
    val menuItems: List<MenuItem>,
    val name: String
)