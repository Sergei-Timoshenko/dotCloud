package com.origamitown.dotcloud.models.post

import android.os.Parcelable
import com.origamitown.dotcloud.models.comment.Comment
import com.origamitown.dotcloud.models.user.UserDetails
import kotlinx.parcelize.Parcelize

sealed class Post() : Parcelable {
    @Parcelize
    data class Photo(
        val photoUrl: String,
        val photoDescription: String?,
        val photoLikeCount: Int,
        val photoComments: List<Comment>?,
        val userDetails: UserDetails,
        val photoId: Int
    ) : Post()
    @Parcelize
    data class Video(
        val videoUrl: String,
        val videoDescription: String?,
        val videoWatchCount: Int,
        val videoComments: List<Comment>?,
        val userDetails: UserDetails,
        val videoId: Int
    ) : Post()
    @Parcelize
    data class Ad(
        val adUrl: String,
        val adId: Int,
    ) : Post()
}
