package com.raj.instaclone.repositories

import com.raj.instaclone.data.fake.FakeServicesImpl
import com.raj.instaclone.models.User
import com.raj.instaclone.sealed.DataResponse
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val fakeServicesImpl: FakeServicesImpl,
) {
    suspend fun signInUser(email: String, password: String): DataResponse<User> {
        return fakeServicesImpl.signInUser(email = email, password = password)
    }

    suspend fun getFakeFeaturedStories() =
        fakeServicesImpl.getFakeFeaturedStories()
}