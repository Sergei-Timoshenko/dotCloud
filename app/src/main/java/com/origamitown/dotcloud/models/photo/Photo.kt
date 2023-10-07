package com.origamitown.dotcloud.models.photo

import com.origamitown.dotcloud.models.comment.Comment
import com.origamitown.dotcloud.models.user.UserDetails

data class Photo(
    val photoUrl: String,
    val photoDescription: String?,
    val photoLikeCount: Int,
    val photoComments: List<Comment>?,
    val userDetails: UserDetails,
    val photoId: Int
)
