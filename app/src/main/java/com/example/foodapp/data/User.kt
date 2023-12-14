package com.example.foodapp.data

data class User(
    val id: Int,
    val name: String, // e.g., "Harry Potter"
    val email: String, // e.g., "harry@gmail.com"
    val password: String, // e.g., "h123123"
    val phone: String, // e.g., "16122339211"
    val imageUri: Int, // e.g., R.drawable.ic_avatar_male
    val bio: String = "" // Default value is an empty string
)
