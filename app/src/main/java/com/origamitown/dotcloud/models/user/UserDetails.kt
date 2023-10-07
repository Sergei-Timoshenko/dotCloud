package com.origamitown.dotcloud.models.user

data class UserDetails(
    val username: String,
    val userPhotoUrl: String? = null,
    val userId: Int
)
