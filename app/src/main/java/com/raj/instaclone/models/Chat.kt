package com.raj.instaclone.models

data class Chat(
    val id: Int,
    val otherUser: User,
    val lastMessage: Message,
)
