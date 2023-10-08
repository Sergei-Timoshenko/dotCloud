package com.origamitown.dotcloud.models.story

import com.origamitown.dotcloud.models.user.UserDetails

enum class StoryPrivacy {
    FRIENDS, CLOSE_FRIENDS
}

data class Story(
    val storyUrl: String,
    val storyPrivacy: StoryPrivacy = StoryPrivacy.FRIENDS,
    val storyViewCount: Int,
    val userId: Int,
    val storyId: Int
)

data class UserStoryActivity(
    val stories: List<Story> = listOf(),
    val userDetails: UserDetails
)