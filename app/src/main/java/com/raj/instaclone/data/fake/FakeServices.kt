package com.raj.instaclone.data.fake

import com.raj.instaclone.models.Chat
import com.raj.instaclone.models.Featured
import com.raj.instaclone.models.MyNotification
import com.raj.instaclone.models.Post
import com.raj.instaclone.models.Story
import com.raj.instaclone.models.User
import com.raj.instaclone.sealed.DataResponse

interface FakeServices {
    suspend fun signInUser(email: String, password: String): DataResponse<User>

    suspend fun getFakePosts(): DataResponse<List<Post>>

    suspend fun getFakeStories(): DataResponse<List<Story>>

    suspend fun getFakeFeaturedStories(): DataResponse<List<Featured>>

    suspend fun findStoryById(storyId: Int): DataResponse<Story?>

    suspend fun findPostById(postId: Int): DataResponse<Post?>

    suspend fun getFakeUsers(userName: String): DataResponse<List<User>>

    suspend fun findUserByUsername(userName: String): DataResponse<User?>

    suspend fun getFakeNotifications(): DataResponse<List<MyNotification>>

    suspend fun getFakeChats() : DataResponse<List<Chat>>

    suspend fun getFakeOnlineUsers(): DataResponse<List<User>>
}