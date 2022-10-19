package com.raj.instaclone.sealed

import com.raj.instaclone.models.PostReacts
import com.raj.instaclone.models.User

sealed class NotificationType {
    class FollowNotification(val user: User, val followed: Boolean) : NotificationType()

    class ReactsNotification(
        val postId: Int,
        val postCoverUrl: Int,
        val postReacts: PostReacts
    ) : NotificationType()

    class MentionNotification(
        val user: User,
        val postId: Int,
        val commentId: Int,
        val comment: String,
    ) : NotificationType()
}
