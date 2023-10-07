package com.origamitown.dotcloud.models.user.mappers

import com.origamitown.dotcloud.models.user.User
import com.origamitown.dotcloud.models.user.UserDetails

fun userToUserDetails(user: User) : UserDetails =
    UserDetails(
        username = user.username,
        userPhotoUrl = user.userPhotoUrl,
        userId = user.userId
    )