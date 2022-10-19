package com.raj.instaclone.repositories

import com.raj.instaclone.data.fake.FakeServicesImpl
import com.raj.instaclone.models.Chat
import com.raj.instaclone.models.User
import com.raj.instaclone.sealed.DataResponse
import javax.inject.Inject

class ChatsRepository @Inject constructor(
    private val fakeServicesImpl: FakeServicesImpl,
) {
    suspend fun getFakeChats() : DataResponse<List<Chat>>{
        return fakeServicesImpl.getFakeChats()
    }

    suspend fun getFakeOnlineUsers() : DataResponse<List<User>>{
        return fakeServicesImpl.getFakeOnlineUsers()
    }
}