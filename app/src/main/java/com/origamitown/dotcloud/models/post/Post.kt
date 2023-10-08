package com.origamitown.dotcloud.models.post

import android.os.Parcelable
import com.origamitown.dotcloud.models.comment.Comment
import com.origamitown.dotcloud.models.user.UserDetails
import kotlinx.parcelize.Parcelize

sealed class Post {
    data class Photo(
        val photoUrl: String,
        val photoDescription: String?,
        val photoLikeCount: Int,
        val photoComments: List<Comment>?,
        val userDetails: UserDetails,
        val photoId: Int
    ) : Post()
    data class Video(
        val videoUrl: String,
        val videoDescription: String?,
        val videoViewCount: Int,
        val videoComments: List<Comment>?,
        val userDetails: UserDetails,
        val videoId: Int
    ) : Post()
    data class Ad(
        val adName: String,
        val adUrl: String,
        val adPhotoUrl: String? = null,
        val adDescription: String? = null,
        val adId: Int,
    ) : Post()
}
