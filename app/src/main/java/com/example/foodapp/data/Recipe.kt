package com.example.foodapp.data

data class Recipe(
    val id: Int,
    val name: String,
    val ingredients: String,
    val instructions: String,
    val userId: Int,
)
