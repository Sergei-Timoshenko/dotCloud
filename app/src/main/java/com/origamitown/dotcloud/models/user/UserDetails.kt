package com.origamitown.dotcloud.models.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDetails(
    val username: String,
    val userPhotoUrl: String? = null,
    val userId: Int
) : Parcelable
