package com.origamitown.dotcloud.models.user

data class User(
    val name: String,
    val surname: String,
    val username: String,
    val userPhotoUrl: String? = null,
    val userDescription: String? = null,
    val userId: Int
)
