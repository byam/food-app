package com.example.foodapp.data

data class User(
    val id: Int,
    val name: String, // Harry Potter
    val email: String, // harry@gmail.com
    val password: String, // h123123
    val phone: String, // 16122339211
    val imageUri: Int, // https://myimage.png
    val bio: String = "",
)
