package com.origamitown.dotcloud.models.video

import com.origamitown.dotcloud.models.comment.Comment
import com.origamitown.dotcloud.models.user.UserDetails

data class Video(
    val videoUrl: String,
    val videoDescription: String?,
    val videoWatchCount: Int,
    val videoComments: List<Comment>?,
    val userDetails: UserDetails,
    val videoId: Int
)
