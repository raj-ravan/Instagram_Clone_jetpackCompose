package com.raj.instaclone.data.fake

import com.raj.instaclone.R
import com.raj.instaclone.UserSession
import com.raj.instaclone.models.Chat
import com.raj.instaclone.models.Featured
import com.raj.instaclone.models.Message
import com.raj.instaclone.models.MyNotification
import com.raj.instaclone.models.Post
import com.raj.instaclone.models.PostReacts
import com.raj.instaclone.models.Story
import com.raj.instaclone.models.User
import com.raj.instaclone.sealed.DataResponse
import com.raj.instaclone.sealed.Error
import com.raj.instaclone.sealed.NotificationType
import kotlinx.coroutines.delay
import java.util.*
import kotlin.random.Random

class FakeServicesImpl : FakeServices {
    private val users = listOf(
        User(
            userId = 1,
            userName = "jatinyadav5004",
            name = "Jatin Yadav",
            profile = R.drawable.camilla,
        ),
        User(
            userId = 2,
            userName = "akanksha042",
            name = "Akanksha Srivastava",
            profile = R.drawable.weeknd,
        ),
        User(
            userId = 3,
            userName = "_.pavithra",
            name = "Pavihta K S ",
            profile = R.drawable.elon_musk,
        ),
        User(
            userId = 4,
            userName = "praharsh_kumar",
            name = "Praharsh Kumar",
            profile = R.drawable.the_rock,
        ),
    )
    private val posts = mutableListOf<Post>()
    private val stories = mutableListOf<Story>()
    private val images = listOf(
        R.drawable.cn_tower,
        R.drawable.effiel_tower,
        R.drawable.eiffel_tower_night,
        R.drawable.explorer_girl_with_map,
        R.drawable.girl_with_landscape,
        R.drawable.james_web_space_image_1,
        R.drawable.mountain_coast,
        R.drawable.oregon_coast,
        R.drawable.pacific_ocean_coast,
        R.drawable.space_explodusions,
        R.drawable.wine_cups,
    )

    init {
        /** Creating the fake posts */
        images.forEachIndexed { index, image ->
            posts.add(
                Post(
                    id = index,
                    user = users.random(),
                    location = listOf(
                        "Delhi, India",
                        "Banglore, India ",
                        "Kolkata, India",
                        "Mumbai, India",
                        "Bhopal, India",
                    ).random(),
                    images = images.shuffled().take((Random.nextInt(1, 5))),
                    caption = listOf(
                        "Good Morning ❤️",
                        "Good Evening 🤩✨",
                        "On fire ! 🔥🔥🔥"
                    ).random(),
                    reacts = PostReacts(
                        recentUser = User(
                            userId = 5,
                            userName = "ed_sheeran",
                            profile = R.drawable.ed_sheeran,
                        ),
                        othersCount = (Math.random() * 100000).toInt(),
                    ),
                )
            )
        }
        /** Creating the fake stories */
        stories.addAll(
            users.map {
                Story(
                    id = it.userId,
                    url = images[Random.nextInt(0, images.size)],
                    user = it
                )
            }
        )
    }

    override suspend fun signInUser(email: String, password: String): DataResponse<User> {
        /** Faking the process of authenticating actual user, wait for 3 seconds and then go */
        delay(3000)
        return DataResponse.Success(
            data = User(
                userId = 1,
                name = "Raj Narayan Mishra",
                userName = "raj@demon",
                email = email,
                profile = R.drawable.my_profile,
                token = "fake-token-baby-haha",
                followers = users,
                following = users,
            ).also { user ->
                user.posts = posts.map { post -> post.copy(user = user) }
            }
        )
    }

    override suspend fun getFakeNotifications(): DataResponse<List<MyNotification>> {
        /** Wait for 4 seconds to simulate the loading from server process */
        delay(4000)

        val milliSecondsInDay = 24 * 60 * 60 * 1000
        val milliSecondsInHour = 60 * 60 * 1000
        val todayDateAsLong = Date().time
        val yesterdayDateAsLong = Date().time.minus(milliSecondsInDay)

        /** now we return the fake notifications for today and yesterday */
        return DataResponse.Success(
            data = listOf(
                MyNotification(
                    id = 1,
                    type = NotificationType.FollowNotification(
                        user = users[0],
                        followed = true,
                    ),
                    time = todayDateAsLong
                ),
                MyNotification(
                    id = 3,
                    type = NotificationType.ReactsNotification(
                        postId = posts[1].id,
                        postCoverUrl = posts[1].images.first(),
                        postReacts = PostReacts(
                            recentUser = users[2],
                            othersCount = Random.nextInt(100, 12000)
                        ),
                    ),
                    time = todayDateAsLong.minus(2 * milliSecondsInHour)
                ),
                MyNotification(
                    id = 4,
                    type = NotificationType.MentionNotification(
                        user = users[3],
                        postId = posts[1].id,
                        commentId = 1,
                        comment = "Exactly! 🤩"
                    ),
                    time = todayDateAsLong.minus(3 * milliSecondsInHour)
                ),

                MyNotification(
                    id = 5,
                    type = NotificationType.FollowNotification(
                        user = users[3],
                        followed = false,
                    ),
                    time = yesterdayDateAsLong
                ),
                MyNotification(
                    id = 7,
                    type = NotificationType.ReactsNotification(
                        postId = posts[2].id,
                        postCoverUrl = posts[2].images.first(),
                        postReacts = PostReacts(
                            recentUser = users[2],
                            othersCount = Random.nextInt(100, 12000)
                        ),
                    ),
                    time = yesterdayDateAsLong.minus(2 * milliSecondsInHour)
                ),
                MyNotification(
                    id = 8,
                    type = NotificationType.MentionNotification(
                        user = users[1],
                        postId = posts[2].id,
                        commentId = 2,
                        comment = "Thank you ma'am ❤️"
                    ),
                    time = yesterdayDateAsLong.minus(3 * milliSecondsInHour)
                ),
            )
        )
    }

    override suspend fun getFakePosts(): DataResponse<List<Post>> {
        return DataResponse.Success(data = posts)
    }

    override suspend fun getFakeChats(): DataResponse<List<Chat>> {
        return DataResponse.Success(
            data = users.mapIndexed {index, user->
                Chat(
                    id = index,
                    otherUser = user,
                    lastMessage = Message(
                        id = index,
                        body = listOf(
                            "Hi Pavithra,how are you? ✨",
                            "Wanna go out?🙄",
                            "Thank you.🤩",
                            "I can't talk right now 😔"
                        )[index],
                        sender = user,
                        receiver = UserSession.user,
                        time = "${index}h",
                    )
                )
            }
        )
    }

    override suspend fun getFakeOnlineUsers(): DataResponse<List<User>> {
        return DataResponse.Success(
            data = users.filter { it.isOnline }
        )
    }

    override suspend fun getFakeStories(): DataResponse<List<Story>> {
        /** Wait for 4 seconds to simulate the loading from server process */
        delay(4000)
        return DataResponse.Success(data = stories)
    }

    override suspend fun getFakeFeaturedStories(): DataResponse<List<Featured>> {
        return UserSession.user?.let {
            DataResponse.Success(
                data = images.mapIndexed { index, drawable ->
                    Featured(
                        id = index,
                        user = it,
                        title = "Feat $index",
                        images = listOf(drawable),
                    )
                }
            )
        } ?: DataResponse.Error(error = Error.Network)
    }

    override suspend fun findStoryById(storyId: Int): DataResponse<Story?> {
        return DataResponse.Success(data = stories.find { it.id == storyId })
    }

    override suspend fun findPostById(postId: Int): DataResponse<Post?> {
        return DataResponse.Success(data = posts.find { it.id == postId })
    }

    override suspend fun getFakeUsers(userName: String): DataResponse<List<User>> {
        return DataResponse.Success(data = users)
    }

    override suspend fun findUserByUsername(userName: String): DataResponse<User?> {
        return DataResponse.Success(data = users.find { it.userName == userName })
    }
}