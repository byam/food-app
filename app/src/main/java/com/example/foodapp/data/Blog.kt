package com.example.foodapp.data

data class Blog (
    val id: Int,
    val title: String,
    val content: String,
    val imageUri: Int?,
    val date: String,
    val userId: Int,
)
