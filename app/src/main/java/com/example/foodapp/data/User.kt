package com.example.foodapp.data

data class User(
    val id: Int,
    var name: String, // e.g., "Harry Potter"
    var email: String, // e.g., "harry@gmail.com"
    val password: String, // e.g., "h123123"
    var phone: String, // e.g., "16122339211"
    val imageUri: Int, // e.g., R.drawable.ic_avatar_male
    var bio: String = "" // Default value is an empty string
)
