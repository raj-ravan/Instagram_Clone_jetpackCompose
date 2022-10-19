package com.raj.instaclone.models

import com.raj.instaclone.sealed.NotificationType

data class MyNotification(
    val id: Int,
    val type: NotificationType,
    val time: Long,
)
