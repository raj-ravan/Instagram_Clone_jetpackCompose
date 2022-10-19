package com.raj.instaclone.models

data class Featured(
    val id: Int,
    val title: String,
    val user: User,
    val images: List<Int>,
)
