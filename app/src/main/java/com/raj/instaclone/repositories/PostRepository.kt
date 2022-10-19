package com.raj.instaclone.repositories

import com.raj.instaclone.data.fake.FakeServicesImpl
import com.raj.instaclone.models.Post
import com.raj.instaclone.sealed.DataResponse
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val fakeServicesImpl: FakeServicesImpl,
) {

    suspend fun getFakePosts(): DataResponse<List<Post>> {
        return fakeServicesImpl.getFakePosts()
    }
}