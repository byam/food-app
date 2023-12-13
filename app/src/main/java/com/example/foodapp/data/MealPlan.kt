package com.example.foodapp.data

data class MealPlan(
    val id: Int,
    val date: String,  // Format: "yyyy-MM-dd"
    val mealType: String, // For example, "Breakfast", "Lunch", "Dinner"
    val recipe: String,  // Recipe name: "Pizza"
    val userId: Int,
)
