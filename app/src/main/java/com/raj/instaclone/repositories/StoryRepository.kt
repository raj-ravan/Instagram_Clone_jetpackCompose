package com.raj.instaclone.repositories

import com.raj.instaclone.data.fake.FakeServicesImpl
import com.raj.instaclone.models.Story
import com.raj.instaclone.sealed.DataResponse
import javax.inject.Inject

class StoryRepository @Inject constructor(
    private val fakeServicesImpl: FakeServicesImpl,
) {
    suspend fun getStories(): DataResponse<List<Story>> {
        return fakeServicesImpl.getFakeStories()
    }
}